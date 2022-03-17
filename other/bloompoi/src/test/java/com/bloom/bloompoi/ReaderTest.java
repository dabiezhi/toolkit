package com.bloom.bloompoi;

import com.bloom.bloompoi.exception.ExcelException;
import com.bloom.bloompoi.model.CardSecret;
import com.bloom.bloompoi.reader.ExcelResult;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Created by mao on 2018/2/21.
 */
public class ReaderTest {

    @Test
    public void testReadList() throws ExcelException {
        List<CardSecret> excelResult = Reader.of(CardSecret.class).from("/Users/taosy/Documents/卡密列表.xls").sheet(0)
            .startRow(1).asList();
        System.out.println(excelResult.size());
    }

    @Test
    public void testReadResult() throws ExcelException {
        ExcelResult<CardSecret> excelResult = Reader.of(CardSecret.class).from("/Users/taosy/Documents/卡密列表.xls")
            .asResult();
        excelResult.setErrorFilePath("/Users/taosy/Documents/卡密列表.xls");
        Writer.of().errors(excelResult.errors()).to("/Users/taosy/Documents/卡密列表2.xls");
        System.out.println(excelResult);
    }

}
