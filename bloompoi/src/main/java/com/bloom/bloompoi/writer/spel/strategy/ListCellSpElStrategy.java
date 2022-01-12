/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.bloom.bloompoi.writer.spel.strategy;

import com.bloom.bloompoi.Constant;
import com.bloom.bloompoi.core.utils.ReflectUtil;
import com.bloom.bloompoi.writer.CellWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * SpEL集合表达式解析
 *
 * @author bloom
 * @version V1.0
 * @since 2022-01-06 23:01
 */
public class ListCellSpElStrategy implements CellSpElStrategy {

    @Override
    public void handle(CellWrapper cellWrapper) {
        if (!cellWrapper.getCellValue().contains(Constant.EXCEL_LIST_SPEL)) {
            return;
        }
        String value = subBracket(cellWrapper.getCellValue());
        List<?> list = (List<?>) ReflectUtil.getFieldValue(cellWrapper.getData(), value);
        for (int j = 0; j < list.size(); j++) {
            Cell cell = cellWrapper.getCell();
            cell = getVerticalValueByList(cellWrapper.getSheet(), cellWrapper.getRow(), cellWrapper.getRowMap(),
                cell.getColumnIndex(), j);
            String spElKey = sub(cellWrapper.getCellValue().replace(Constant.ASTERISK, String.valueOf(j)));
            String spElValue = cellWrapper.getParser().parseExpression(spElKey)
                .getValue(cellWrapper.getContext(), String.class);
            cell.setCellValue(spElValue);
        }
    }

    /**
     * 获取单元格对象(集合竖直扩展需要创建单元格对象并赋值)
     *
     * @param sheet    sheet对象
     * @param row      行对象
     * @param rowMap   行关系映射
     * @param colIndex 列号
     * @param rowIndex 行号
     * @return 单元格对象
     */
    private Cell getVerticalValueByList(Sheet sheet, Row row, Map<Integer, Row> rowMap, int colIndex, int rowIndex) {
        Cell cell = row.getCell(colIndex);
        if (rowIndex != 0) {
            Row rowCopy = (null == rowMap.get(row.getRowNum() + rowIndex)) ?
                sheet.createRow(row.getRowNum() + rowIndex) :
                rowMap.get(row.getRowNum() + rowIndex);
            rowMap.put(row.getRowNum() + rowIndex, rowCopy);
            return rowCopy.createCell(colIndex);
        }
        return cell;
    }
}
