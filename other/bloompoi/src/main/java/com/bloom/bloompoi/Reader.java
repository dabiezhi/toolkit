package com.bloom.bloompoi;

import com.bloom.bloompoi.core.utils.StringUtil;
import com.bloom.bloompoi.exception.ExcelException;
import com.bloom.bloompoi.reader.ExcelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

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
public class Reader<T> {

    private Class<T> type;

    public ExcelReader<T> from(String fileUrl) throws ExcelException {
        if (StringUtil.isBlank(fileUrl)) {
            throw new IllegalArgumentException("fileUrl cannot be empty");
        }
        try {
            return this.from(new FileInputStream(new File(fileUrl)));
        } catch (Exception e) {
            throw new ExcelException(e);
        }
    }

    private ExcelReader<T> from(InputStream inputStream) throws ExcelException {
        if (null == inputStream) {
            throw new IllegalArgumentException("fileUrl cannot be empty");
        }
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            return new ExcelReader<>(workbook, type);
        } catch (Exception e) {
            throw new ExcelException(e);
        }
    }

    public static <T> Reader<T> of(Class<T> type) {
        return new Reader<T>().type(type);
    }

    private Reader<T> type(Class<T> type) {
        this.type = type;
        return this;
    }
}
