package com.bloom.bloompoi.writer;

import com.bloom.bloompoi.Constant;
import com.bloom.bloompoi.Writer;
import com.bloom.bloompoi.annotation.ExcelField;
import com.bloom.bloompoi.core.utils.CollectionUtil;
import com.bloom.bloompoi.core.utils.ExcelUtil;
import com.bloom.bloompoi.core.utils.StringUtil;
import com.bloom.bloompoi.enums.ExcelType;
import com.bloom.bloompoi.exception.ExcelException;
import com.bloom.bloompoi.reader.ValidResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSimpleShape;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import lombok.SneakyThrows;

/**
 * 数据写入Excel接口
 *
 * @author bloom
 * @date 2018/2/4
 */
public interface ExcelWriter extends Constant {

    /**
     * 默认的导出方法
     *
     * @param writer       Exporter对象
     * @param outputStream 输出流
     * @param <T>          Java类型
     * @throws ExcelException Excel异常
     */
    default <T> void export(Writer<T> writer, OutputStream outputStream) throws ExcelException {
        Collection<T> data = writer.getData();
        if (CollectionUtil.isEmpty(data)) {
            throw new ExcelException("Export excel data is empty.");
        }

        try (Workbook workbook = createWork(writer)) {
            Sheet sheet;
            CellStyle headerStyle;
            CellStyle columnStyle;
            CellStyle titleStyle;

            T data0 = data.iterator().next();
            // Set Excel header
            Iterator<T> iterator = data.iterator();
            // 获取列名-列号的关系映射
            Map<String, Integer> writeFieldNames = ExcelUtil.getFieldNameAndColMap(data0.getClass());
            // 开始行
            int startRow = writer.getStartRow();
            sheet = workbook.createSheet(ExcelUtil.getSheetName(data0));

            if (null != writer.getTitleStyle()) {
                titleStyle = writer.getTitleStyle().apply(workbook);
            } else {
                titleStyle = this.defaultTitleStyle(workbook);
            }

            if (null != writer.getHeaderStyle()) {
                headerStyle = writer.getHeaderStyle().apply(workbook);
            } else {
                headerStyle = this.defaultHeaderStyle(workbook);
            }

            if (null != writer.getColumnStyle()) {
                columnStyle = writer.getColumnStyle().apply(workbook);
            } else {
                columnStyle = this.defaultColumnStyle(workbook);
            }

            String headerTitle = writer.getHeaderTitle();
            int colIndex = 0;
            if (null != headerTitle) {
                colIndex = 1;
                this.writeTitleRow(headerStyle, sheet, headerTitle, writeFieldNames.keySet().size());
            }
            this.writeColumnNames(colIndex, titleStyle, sheet, writeFieldNames);
            startRow += colIndex;
            this.writeRows(sheet, columnStyle, iterator, writeFieldNames, startRow);
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            throw new ExcelException(e);
        }
    }

    /**
     * 创建Workbook对象
     *
     * @param writer Exporter对象
     * @param <T>    Java类型
     * @return Workbook Workbook
     * @throws ExcelException Excel异常
     */
    default <T> Workbook createWork(Writer<T> writer) throws ExcelException {
        try {
            if (StringUtil.isNotBlank(writer.getTemplatePath())) {
                InputStream in = ExcelWriter.class.getClassLoader().getResourceAsStream(writer.getTemplatePath());
                if (null == in) {
                    in = new FileInputStream(writer.getTemplatePath());
                }
                return WorkbookFactory.create(in);
            } else {
                return writer.getExcelType().equals(ExcelType.XLSX) ? new XSSFWorkbook() : new HSSFWorkbook();
            }
        } catch (Exception e) {
            throw new ExcelException(e);
        }
    }

    /**
     * 生成标题头行
     *
     * @param cellStyle   单元格样式
     * @param sheet       sheet对象
     * @param title       标题名称
     * @param maxColIndex 标题占的最大列号
     */
    default void writeTitleRow(CellStyle cellStyle, Sheet sheet, String title, int maxColIndex) {
        Row titleRow = sheet.createRow(0);
        for (int i = 0; i < maxColIndex; i++) {
            Cell cell = titleRow.createCell(i);
            if (i == 0) {
                cell.setCellValue(title);
            }
            cell.setCellStyle(cellStyle);
        }
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, maxColIndex));
    }

    /**
     * 生成标题行
     *
     * @param rowIndex    行号
     * @param cellStyle   单元格样式
     * @param sheet       sheet对象
     * @param columnNames 获取列名-列号的关系映射
     */
    default void writeColumnNames(int rowIndex, CellStyle cellStyle, Sheet sheet, Map<String, Integer> columnNames) {
        Row rowHead = sheet.createRow(rowIndex);
        columnNames.forEach((k, v) -> {
            Cell cell = rowHead.createCell(v);
            if (null != cellStyle) {
                cell.setCellStyle(cellStyle);
            }
            cell.setCellValue(k);
        });
    }

    /**
     * 写入行数据
     *
     * @param sheet           sheet对象
     * @param columnStyle     单元格样式
     * @param iterator        行迭代器
     * @param writeFieldNames 获取列名-列号的关系映射
     * @param startRow        开始行
     * @param <T>             Java类型
     */
    default <T> void writeRows(Sheet sheet, CellStyle columnStyle, Iterator<T> iterator,
        Map<String, Integer> writeFieldNames, int startRow) {
        for (int rowNum = startRow; iterator.hasNext(); rowNum++) {
            T item = iterator.next();
            BeanMap beanMap = BeanMap.create(item);
            Row row = sheet.createRow(rowNum);
            List<Field> fields = ExcelUtil.getAndSaveFields(item.getClass());
            fields.forEach(field -> {
                long start1 = System.currentTimeMillis();
                ExcelField excelField = field.getAnnotation(ExcelField.class);
                Integer colNum = writeFieldNames.get(excelField.columnName());
                if (null != colNum) {
                    Cell cell = row.createCell(colNum);
                    //                    String value = ExcelUtil.getColumnValue(item, field);
                    //TODO 该处需要进行类型转换优化
                    cell.setCellValue(String.valueOf(beanMap.get(field.getName())));
                    cell.setCellStyle(columnStyle);
                    long end1 = (System.currentTimeMillis() - start1);
                    System.out.println(String.format("写%d行%d列数据所属时间:", row.getRowNum(), colNum) + end1);
                }
            });
        }
    }

    /**
     * 删除模板中的图形，文本框
     *
     * @param sheet     sheet对象
     * @param excelType 文件类型
     */
    default void clearShape(Sheet sheet, ExcelType excelType) {
        // 删除模板中的图形，文本框
        if (ExcelType.XLS.equals(excelType)) {
            HSSFPatriarch drawing = (HSSFPatriarch) sheet.getDrawingPatriarch();
            if (null != drawing) {
                drawing.clear();
            }
        } else if (ExcelType.XLSX.equals(excelType)) {
            // xssf找不到清除的方法，使用隐藏代替
            Drawing<?> drawing = sheet.getDrawingPatriarch();
            if (null != drawing) {
                for (Object o : drawing) {
                    if (o instanceof XSSFSimpleShape) {
                        XSSFSimpleShape shape = (XSSFSimpleShape) o;
                        XSSFClientAnchor anchor = (XSSFClientAnchor) shape.getAnchor();
                        // 清空文本
                        shape.clearText();
                        // 转换成线形状，可以不转
                        shape.setShapeType(ShapeType.LINE.ooxmlId);
                        // 使得图形可移动和改变大小
                        anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);
                        // 移动图形到表单左上角，并隐藏大小
                        anchor.setDx1(0);
                        anchor.setDy1(0);
                        anchor.setDx2(0);
                        anchor.setDy2(0);
                        anchor.setRow1(0);
                        anchor.setCol1(0);
                        anchor.setRow2(0);
                        anchor.setCol2(0);
                    }
                }
            }
        }
    }

    /**
     * 通过spel表达式解析模板导出Excel
     *
     * @param writer       Exporter对象
     * @param outputStream 输出流
     * @param <T>          Java类型
     * @throws ExcelException Excel异常
     */
    default <T> void exportBySpEl(Writer<T> writer, OutputStream outputStream) throws ExcelException {
        try (Workbook workbook = createWork(writer)) {
            for (Integer i : writer.getDataMap().keySet()) {
                getSheetAtBySpEl(workbook, writer, i);
            }
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            throw new ExcelException(e);
        }
    }

    /**
     * 读取Excel数据,通过SpEl表达式反向赋值
     *
     * @param workbook workbook对象
     * @param writer   导出对象
     * @param index    sheet索引
     */
    default <T> void getSheetAtBySpEl(Workbook workbook, Writer<T> writer, int index) throws ExcelException {
        Sheet sheet = workbook.getSheetAt(index);
        Iterator<Row> rows = sheet.rowIterator();
        Map<Integer, Row> rowMap = new HashMap<>();
        Object obj = writer.getDataMap().get(index);
        // 使用SpEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        // SpEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable(obj.getClass().getSimpleName(), obj);

        while (rows.hasNext()) {
            Row row = rows.next();
            int colNum = row.getLastCellNum();
            for (int i = 0; i < colNum; i++) {
                Cell cell = row.getCell(i);
                if (null == cell) {
                    cell = row.createCell(i);
                }
                String value = ExcelUtil.getCellValue(cell);
                CellWrapper cellWrapper = CellWrapper.of().workbook(workbook).row(row).rowMap(rowMap).sheet(sheet)
                    .data(obj).parser(parser).context(context).cell(cell).cellValue(value);
                writer.getCellSpElStrategyList().forEach(u -> u.handle(cellWrapper));
            }
        }
    }

    /**
     * 导出Excel基础数据的校验结果(错误数据做标红、添加批注处理)
     *
     * @param writer       Exporter对象
     * @param outputStream 输出流
     * @param <T>          Java类型
     * @throws ExcelException Excel异常
     */
    @SneakyThrows
    default <T> void exportByResult(Writer<T> writer, OutputStream outputStream) throws ExcelException {
        Workbook workbook = null;
        File file = new File("/Users/taosy/Documents/卡密列表.xls");
        workbook = new HSSFWorkbook(new FileInputStream(file));
        // 定义异常单元格前景色
        Map<String, Object> properties = new HashMap<>();
        properties.put(CellUtil.FILL_FOREGROUND_COLOR, IndexedColors.RED.index);
        properties.put(CellUtil.FILL_PATTERN, FillPatternType.SOLID_FOREGROUND);

        // 标识异常单元格
        Sheet sheet = workbook.getSheetAt(0);
        for (ValidResult item : writer.getErrors()) {
            int rowNum = null == item.getRowNum() ? 0 : item.getRowNum();
            Row row = sheet.getRow(rowNum);
            int colNum = null == item.getColNum() ? row.getLastCellNum() + 1 : item.getColNum();
            Cell cell = row.getCell(colNum);
            if (null == cell) {
                cell = row.createCell(colNum);
            }
            // 改变样式，避免影响其他单元格样式，使用CellUtil
            CellUtil.setCellStyleProperties(cell, properties);
            if (null == item.getRowNum() || null == item.getColNum()) {
                // 增加错误说明
                cell.setCellValue(item.getMsg());
            } else {
                // 增加批注错误信息提示
                if (ExcelType.XLS.equals(writer.getExcelType())) {
                    HSSFComment comment = getHSSFComment(sheet, item);
                    if (null == cell.getCellComment()) {
                        cell.setCellComment(comment);
                    }
                } else {
                    XSSFComment comment = getXSSFDrawing(sheet, item);
                    if (null == cell.getCellComment()) {
                        cell.setCellComment(comment);
                    }
                }
            }
        }
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();

    }

    /**
     * 添加批注
     *
     * @param sheet sheet对象
     * @param item  ValidResult对象
     * @return HSSFComment对象
     */
    default HSSFComment getHSSFComment(Sheet sheet, ValidResult item) {
        // 增加批注错误信息提示
        HSSFPatriarch patriarch = (HSSFPatriarch) sheet.createDrawingPatriarch();
        // 创建批注位置
        HSSFClientAnchor anchor = patriarch
            .createAnchor(0, 0, 0, 0, item.getColNum() + 3, item.getRowNum(), item.getColNum() + 6, item.getRowNum());
        // 创建批注
        HSSFComment comment = patriarch.createCellComment(anchor);
        // 设置批注内容
        comment.setString(new HSSFRichTextString(item.getMsg()));
        comment.setVisible(false);
        return comment;
    }

    /**
     * 添加批注
     *
     * @param sheet sheet对象
     * @param item  ValidResult对象
     * @return XSSFComment对象
     */
    default XSSFComment getXSSFDrawing(Sheet sheet, ValidResult item) {
        // 增加批注错误信息提示
        XSSFDrawing patriarch = (XSSFDrawing) sheet.createDrawingPatriarch();
        // 创建批注位置
        XSSFClientAnchor anchor = patriarch
            .createAnchor(0, 0, 0, 0, item.getColNum() + 3, item.getRowNum(), item.getColNum() + 6, item.getRowNum());
        // 创建批注
        XSSFComment comment = patriarch.createCellComment(anchor);
        // 设置批注内容
        comment.setString(new XSSFRichTextString(item.getMsg()));
        comment.setVisible(false);
        return comment;
    }

    <T> void export(Writer<T> writer) throws ExcelException;

    <T> void exportBySpEl(Writer<T> writer) throws ExcelException;

    <T> void exportByResult(Writer<T> writer) throws ExcelException;
}
