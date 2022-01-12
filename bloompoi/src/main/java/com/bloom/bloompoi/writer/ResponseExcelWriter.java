package com.bloom.bloompoi.writer;

import com.bloom.bloompoi.Writer;
import com.bloom.bloompoi.core.utils.CollectionUtil;
import com.bloom.bloompoi.enums.ExcelType;
import com.bloom.bloompoi.exception.ExcelException;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;

/**
 * ServletResponse出口
 *
 * @author bloom
 * @date 2018/2/25
 */
public class ResponseExcelWriter implements ExcelWriter {

    private ResponseWrapper wrapper;

    public ResponseExcelWriter(ResponseWrapper wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public <T> void export(Writer<T> writer) throws ExcelException {
        HttpServletResponse servletResponse = this.wrapper.getServletResponse();
        try {
            String fileName = wrapper.getFileName();
            servletResponse.setContentType("application/x-xls");
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
            servletResponse.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            ExcelType excelType = ExcelType.getExcelType(fileName);
            writer.setExcelType(excelType);
            if (writer.isIfByTemplate()) {
                this.exportBySpEl(writer, servletResponse.getOutputStream());
            }
            if (CollectionUtil.isNotEmpty(writer.getErrors())) {
                this.exportByResult(writer, servletResponse.getOutputStream());
            } else {
                this.export(writer, servletResponse.getOutputStream());
            }
        } catch (Exception e) {
            throw new ExcelException(e);
        }
    }

    @Override
    public <T> void exportBySpEl(Writer<T> writer) throws ExcelException {
        HttpServletResponse servletResponse = this.wrapper.getServletResponse();
        try {
            String fileName = wrapper.getFileName();
            servletResponse.setContentType("application/x-xls");
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            servletResponse.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            ExcelType excelType = ExcelType.getExcelType(fileName);
            writer.setExcelType(excelType);
            this.exportBySpEl(writer, servletResponse.getOutputStream());
        } catch (Exception e) {
            throw new ExcelException(e);
        }
    }

    @Override
    public <T> void exportByResult(Writer<T> writer) throws ExcelException {
        HttpServletResponse servletResponse = this.wrapper.getServletResponse();
        try {
            String fileName = wrapper.getFileName();
            servletResponse.setContentType("application/x-xls");
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            servletResponse.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            this.exportByResult(writer, servletResponse.getOutputStream());
        } catch (Exception e) {
            throw new ExcelException(e);
        }
    }
}
