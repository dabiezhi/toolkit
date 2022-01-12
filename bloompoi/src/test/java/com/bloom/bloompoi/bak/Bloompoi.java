package com.bloom.bloompoi.bak;

import com.bloom.bloompoi.Writer;
import com.bloom.bloompoi.exception.ExcelException;
import com.bloom.bloompoi.reader.ExcelReader;
import com.bloom.bloompoi.writer.FileExcelWriter;
import com.bloom.bloompoi.writer.ResponseExcelWriter;
import com.bloom.bloompoi.writer.ResponseWrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Excel Bloom
 * <p>
 * 提供方法去读取和写入Excel文件没有任何实现.
 *
 * @author bloom
 * @date 2018/2/25
 */
public class Bloompoi {

    /**
     * <p>
     * Export Excel参数配置.
     */
    private Writer writer;

    /**
     * 将data设置到Exporter容器中.
     *
     * @param data 集合数据
     * @param <T>  Java类型
     * @return self
     */
    public <T> Bloompoi export(Collection<T> data) {
        return this.export(Writer.of(data));
    }

    /**
     * 自定义导出对象用于导出Excel.
     *
     * @param <T>      Java类型
     * @param writer exporter容器
     * @return self
     */
    public <T> Bloompoi export(Writer<T> writer) {
        this.writer = writer;
        return this;
    }

    /**
     * 将导出的数据写到文件中.
     *
     * @param file 文件对象
     * @throws ExcelException Excel异常
     */
    @SuppressWarnings({ "unchecked" })
    public void writeAsFile(File file) throws ExcelException {
        new FileExcelWriter(file).export(writer);
    }

    /**
     * 通过spel表达式解析模板将导出的数据写到文件中.
     *
     * @param file 文件对象
     * @throws ExcelException Excel异常
     */
    @SuppressWarnings({ "unchecked" })
    public void writeAsFileBySpEl(File file) throws ExcelException {
        new FileExcelWriter(file).exportBySpEl(writer);
    }

    /**
     * 将读取文件的校验结果写到读取文件中,作错误单元格标红、添加批注处理.
     *
     * @param file 文件对象
     * @throws ExcelException Excel异常
     */
    @SuppressWarnings({ "unchecked" })
    public void writeAsFileByResult(File file) throws ExcelException {
        new FileExcelWriter(file).exportByResult(writer);
    }

    /**
     * 将导出的数据写到response中.
     *
     * @param wrapper HttpServletResponse包装类
     * @throws ExcelException Excel异常
     */
    @SuppressWarnings({ "unchecked" })
    public void writeAsResponse(ResponseWrapper wrapper) throws ExcelException {
        new ResponseExcelWriter(wrapper).export(writer);
    }

    /**
     * <p>
     * 通过spel表达式解析模板将导出的数据写到response中.
     *
     * @param wrapper HttpServletResponse包装类
     * @throws ExcelException Excel异常
     */
    @SuppressWarnings({ "unchecked" })
    public void writeAsResponseBySpEl(ResponseWrapper wrapper) throws ExcelException {
        new ResponseExcelWriter(wrapper).exportBySpEl(writer);
    }

    /**
     * 读取Excel文件
     *
     * @param fileUrl 文件路径
     * @param type    保存的Java类型
     * @param <T>     Java类型
     * @return 读取Excel行信息
     * @throws ExcelException Excel异常
     */
    public <T> ExcelReader<T> read(String fileUrl, Class<T> type) throws ExcelException {
        try {
            return this.read(new FileInputStream(new File(fileUrl)), type);
        } catch (Exception e) {
            throw new ExcelException(e);
        }
    }

    /**
     * 读取Excel文件
     *
     * @param file 文件对象
     * @param type 保存的Java类型
     * @param <T>  Java类型
     * @return 读取Excel行信息
     * @throws ExcelException Excel异常
     */
    public <T> ExcelReader<T> read(File file, Class<T> type) throws ExcelException {
        try {
            return this.read(new FileInputStream(file), type);
        } catch (Exception e) {
            throw new ExcelException(e);
        }
    }

    /**
     * 读取Excel
     *
     * @param inputStream 文件流
     * @param type        保存的Java类型
     * @param <T>         Java类型
     * @return 读取Excel行信息
     * @throws ExcelException Excel异常
     */
    private <T> ExcelReader<T> read(InputStream inputStream, Class<T> type) throws ExcelException {
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            return new ExcelReader<>(workbook, type);
        } catch (Exception e) {
            throw new ExcelException(e);
        }
    }

    /**
     * 静态方法new实例
     *
     * @return 实例
     */
    public static Bloompoi me() {
        return new Bloompoi();
    }

}
