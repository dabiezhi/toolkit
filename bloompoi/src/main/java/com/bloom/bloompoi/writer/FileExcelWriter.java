package com.bloom.bloompoi.writer;

import com.bloom.bloompoi.Writer;
import com.bloom.bloompoi.core.utils.CollectionUtil;
import com.bloom.bloompoi.enums.ExcelType;
import com.bloom.bloompoi.exception.ExcelException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Write data to Excel file.
 *
 * @author bloom
 * @date 2018/2/25
 */
public class FileExcelWriter implements ExcelWriter {

    /**
     * 文件保存路径
     */
    private final File savePath;

    @Override
    public <T> void export(Writer<T> writer) throws ExcelException {
        if (null == savePath) {
            throw new ExcelException("Save the Excel path can not be null.");
        }
        try {
            ExcelType excelType = ExcelType.getExcelType(savePath.getName());
            writer.setExcelType(excelType);
            OutputStream outputStream = new FileOutputStream(savePath);
            if (writer.isIfByTemplate()) {
                this.exportBySpEl(writer, outputStream);
            } else if (CollectionUtil.isNotEmpty(writer.getErrors())) {
                this.exportByResult(writer, outputStream);
            } else {
                this.export(writer, outputStream);
            }
        } catch (Exception e) {
            throw new ExcelException(e);
        }
    }

    public FileExcelWriter(File file) {
        this.savePath = file;
    }

    @Override
    public <T> void exportBySpEl(Writer<T> writer) throws ExcelException {
        if (null == savePath) {
            throw new ExcelException("Save the Excel path can not be null.");
        }
        try {
            ExcelType excelType = ExcelType.getExcelType(savePath.getName());
            writer.setExcelType(excelType);
            this.exportBySpEl(writer, new FileOutputStream(savePath));
        } catch (Exception e) {
            throw new ExcelException(e);
        }
    }

    @Override
    public <T> void exportByResult(Writer<T> writer) throws ExcelException {
        if (null == savePath) {
            throw new ExcelException("Save the Excel path can not be null.");
        }
        try {
            ExcelType excelType = ExcelType.getExcelType(savePath.getName());
            writer.setExcelType(excelType);
            this.exportByResult(writer, new FileOutputStream(savePath));
        } catch (Exception e) {
            throw new ExcelException(e);
        }
    }
}
