package com.bloom.bloompoi.reader;

import com.bloom.bloompoi.core.utils.ExcelUtil;
import com.bloom.bloompoi.core.utils.Pair;
import com.bloom.bloompoi.core.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 读取Excel文件转换成集合
 * <p>
 * 该类用于读取Excel文档并将文档中的所有行转换为List
 * <p>
 * 要通过在构造函数中传递一个Workbook对象来解析Excel, the Class is passed to reflect the value for
 * a particular type of reflection. 该类通过反射来得到值
 *
 * @author bloom
 * @date 2018/2/25
 */
public class ExcelReader<T> {

    /**
     * workbook
     */
    private final Workbook workbook;
    /**
     * class
     */
    private final Class<T> type;
    /**
     * 开始读取的行数 默认为1
     */
    private int startRow = 1;
    /**
     * 开始读取的sheet 默认为0
     */
    private Object sheet = 0;
    /**
     * 暂时该字段无用
     */
    private Function<T, ValidResult> validFunction;
    /**
     * 用于存放Excel表中每行每列数据类型的校验结果
     */
    private final ExcelResult<T> excelResult = new ExcelResult<>();
    /**
     * 用于存放列名-列号的映射关系
     */
    private final Map<String, Integer> colMap;

    public ExcelReader(Workbook workbook, Class<T> type) {
        this.workbook = workbook;
        this.type = type;
        colMap = ExcelUtil.getFieldNameAndColMap(type);
    }

    /**
     * 读取Excel表中数据返回相关列名、行号映射关系,数据集合、数据类型校验结果
     *
     * @return ExcelResult Excel结果封装类
     */
    public ExcelResult<T> asResult() {
        List<T> rows = this.asStream().map(Pair::getV).collect(Collectors.toList());
        this.excelResult.setRows(rows);
        this.excelResult.setColMap(colMap);
        return this.excelResult;
    }

    /**
     * 读取Excel表中数据转换成数据集合
     *
     * @return 数据集合
     */
    public List<T> asList() {
        return this.asStream().map(Pair::getV).collect(Collectors.toList());
    }

    /**
     * 读取Excel表中数据转换成Stream流
     *
     * @return Stream流
     */
    private Stream<Pair<Integer, T>> asStream() {
        // 获取Sheet对象
        Sheet sheet;
        if (this.sheet instanceof String) {
            sheet = workbook.getSheet(String.valueOf(this.sheet));
        } else {
            sheet = workbook.getSheetAt((Integer) this.sheet);
        }
        // 获取该Sheet中第一行行号、最后一行行号
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();

        List<Pair<Integer, T>> list = new ArrayList<>(lastRowNum);
        // 从startRowIndex开始，遍历每一行，反射生成实体
        for (int rowNum = firstRowNum + this.startRow; rowNum <= lastRowNum; rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (null == row) {
                continue;
            }
            // 根据row对象反射生成实体
            T item = this.buildItem(row);
            if (null != item) {
                list.add(new Pair<>(rowNum, item));
            }
        }

        return list.stream();
    }

    /**
     * 根据行对象反射生成实体对象
     *
     * @param row 行对象
     * @return 实体
     */
    private T buildItem(Row row) {
        T item = ExcelUtil.newInstance(type);
        if (null == item) {
            return null;
        }
        int firstCellNum = row.getFirstCellNum();
        int lastCellNum = row.getLastCellNum();
        for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
            Cell cell = row.getCell(cellNum);
            String value = ExcelUtil.getCellValue(cell);
            // 校验数据是否符合自定义标准,返回字符串不为null 则不符合自定义标准
            String validMsg = ExcelUtil.validFieldByAnnotation(item, row.getRowNum(), cellNum, value);
            // 该处代码暂无用
            if (null != this.validFunction) {
                ValidResult validResult = validFunction.apply(item);
                validMsg = validResult.getMsg();
            }
            // 如果校验信息不为空,则将错误信息(行号-列号-错误信息)存入到ValidResult对象中
            if (StringUtil.isNotBlank(validMsg)) {
                excelResult.addValidResult(new ValidResult(row.getRowNum(), cellNum, validMsg));
            }
            // 根据(行号-列号-值)反射赋值到正确的Field上
            //TODO 使用BeanMap进行优化
            ExcelUtil.writeToField(item, row.getRowNum(), cellNum, value);
        }
        return item;
    }

    /**
     * 该方法暂无用
     *
     * @param validFunction function对象
     * @return ExcelReader对象
     */
    public ExcelReader<T> valid(Function<T, ValidResult> validFunction) {
        this.validFunction = validFunction;
        return this;
    }

    /**
     * 设置Excel的开始读取行号
     *
     * @param startRowIndex 开始读取行号
     * @return ExcelReader 读取对象
     */
    public ExcelReader<T> startRow(int startRowIndex) {
        this.startRow = startRowIndex;
        return this;
    }

    /**
     * 设置Excel的sheet读取
     *
     * @param sheet sheet的索引或sheet名称
     * @return ExcelReader 读取对象
     */
    public ExcelReader<T> sheet(Object sheet) {
        this.sheet = sheet;
        return this;
    }

}
