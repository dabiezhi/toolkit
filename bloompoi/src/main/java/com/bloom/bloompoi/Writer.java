package com.bloom.bloompoi;

import com.bloom.bloompoi.enums.ExcelType;
import com.bloom.bloompoi.reader.ValidResult;
import com.bloom.bloompoi.writer.FileExcelWriter;
import com.bloom.bloompoi.writer.ResponseExcelWriter;
import com.bloom.bloompoi.writer.ResponseWrapper;
import com.bloom.bloompoi.writer.spel.strategy.CellSpElStrategy;
import com.bloom.bloompoi.writer.spel.strategy.ModelCellSpElStrategy;
import com.bloom.bloompoi.writer.spel.strategy.ListCellSpElStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Exporter
 *
 * @author bloom
 * @version V1.0
 * @since 2022-01-07 09:57
 */
@Getter
@Setter
@Accessors(chain = true)
public class Writer<T> {

    /**
     * 从第几行开始(导出使用)
     */
    private int startRow = 1;
    /**
     * Excel后缀类型(导出使用)
     */
    private ExcelType excelType = ExcelType.XLS;
    /**
     * 自定义标题(默认第一行)(导出使用)
     */
    private String headerTitle;
    /**
     * 自定义样式(导出使用)
     */
    private Function<Workbook, CellStyle> titleStyle;
    private Function<Workbook, CellStyle> headerStyle;
    private Function<Workbook, CellStyle> columnStyle;
    /**
     * toResult方法专用，适用于导入校验结果导出
     */
    private List<ValidResult> errors;

    /**
     * 集合数据(导出使用)
     */
    private Collection<T> data;

    /**
     * SpEl模板路径(导出使用)
     */
    private String templatePath;
    /**
     * 是否模板导出
     */
    private boolean ifByTemplate = false;
    /**
     * SpEl模板导出Map<sheet的索引,每个sheet的对象载体>(导出使用)
     */
    private Map<Integer, Object> dataMap;
    /**
     * 配合SpEl模板导入的策略集合(导出使用)
     */
    private List<CellSpElStrategy> cellSpElStrategyList = new ArrayList<>();

    {
        cellSpElStrategyList.add(new ModelCellSpElStrategy());
        cellSpElStrategyList.add(new ListCellSpElStrategy());
    }

    public static <T> Writer<T> of(Collection<T> data) {
        Writer<T> writer = new Writer<>();
        writer.setData(data);
        return writer;
    }

    public static Writer<Void> of(Object obj) {
        Writer<Void> writer = new Writer<>();
        Map<Integer, Object> map = new HashMap<>();
        map.put(Constant.ZERO, obj);
        writer.setDataMap(map);
        return writer;
    }

    public static Writer<Void> of(Map<Integer, Object> dataMap) {
        Writer<Void> writer = new Writer<>();
        writer.setDataMap(dataMap);
        return writer;
    }

    public static <T> Writer<T> of() {
        return new Writer<>();
    }

    public void to(String fileUrl) {
        new FileExcelWriter(new File(fileUrl)).export(this);
    }

    public void to(File file) {
        new FileExcelWriter(file).export(this);
    }

    public void to(ResponseWrapper wrapper) {
        new ResponseExcelWriter(wrapper).export(this);
    }

    public Writer<T> startRow(int startRow) {
        this.startRow = startRow;
        return this;
    }

    public Writer<T> excelType(ExcelType excelType) {
        this.excelType = excelType;
        return this;
    }

    public Writer<T> headerTitle(String headerTitle) {
        this.headerTitle = headerTitle;
        return this;
    }

    public Writer<T> titleStyle(Function<Workbook, CellStyle> titleStyle) {
        this.titleStyle = titleStyle;
        return this;
    }

    public Writer<T> headerStyle(Function<Workbook, CellStyle> headerStyle) {
        this.headerStyle = headerStyle;
        return this;
    }

    public Writer<T> columnStyle(Function<Workbook, CellStyle> columnStyle) {
        this.columnStyle = columnStyle;
        return this;
    }

    public Writer<T> cellSpElStrategy(CellSpElStrategy strategy) {
        this.cellSpElStrategyList.add(strategy);
        return this;
    }

    public Writer<T> byTemplate(String templatePath) {
        this.templatePath = templatePath;
        this.ifByTemplate = true;
        return this;
    }

    public Writer<T> errors(List<ValidResult> errors) {
        this.errors = errors;
        return this;
    }

}
