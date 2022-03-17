/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.bloom.bloompoi.writer;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * 列-装饰器
 *
 * @author bloom
 * @version V1.0
 * @since 2022-01-06 23:23
 */
@Getter
@Accessors(chain = true)
public class CellWrapper {

    private Workbook workbook;

    private Sheet sheet;

    private Object data;

    private Row row;
    private Map<Integer, Row> rowMap;

    private Cell cell;

    private String cellValue;

    private ExpressionParser parser;

    private StandardEvaluationContext context;

    public static CellWrapper of() {
        return new CellWrapper();
    }

    public CellWrapper workbook(Workbook workbook) {
        this.workbook = workbook;
        return this;
    }

    public CellWrapper sheet(Sheet sheet) {
        this.sheet = sheet;
        return this;
    }

    public CellWrapper data(Object data) {
        this.data = data;
        return this;
    }

    public CellWrapper row(Row row) {
        this.row = row;
        this.rowMap = new HashMap<>();
        return this;
    }

    public CellWrapper rowMap(Map<Integer, Row> rowMap) {
        this.rowMap = rowMap;
        return this;
    }

    public CellWrapper cell(Cell cell) {
        this.cell = cell;
        return this;
    }

    public CellWrapper cellValue(String cellValue) {
        this.cellValue = cellValue;
        return this;
    }

    public CellWrapper parser(ExpressionParser parser) {
        this.parser = parser;
        return this;
    }

    public CellWrapper context(StandardEvaluationContext context) {
        this.context = context;
        context.setVariable(data.getClass().getSimpleName(), data);
        return this;
    }
}
