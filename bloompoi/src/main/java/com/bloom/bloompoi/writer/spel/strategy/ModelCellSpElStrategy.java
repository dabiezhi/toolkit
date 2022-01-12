/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.bloom.bloompoi.writer.spel.strategy;

import com.bloom.bloompoi.Constant;
import com.bloom.bloompoi.writer.CellWrapper;

/**
 * SpEL普通表达式解析
 *
 * @author bloom
 * @version V1.0
 * @since 2022-01-06 23:01
 */
public class ModelCellSpElStrategy implements CellSpElStrategy {

    @Override
    public void handle(CellWrapper cellWrapper) {
        if (!cellWrapper.getCellValue().contains(Constant.EXCEL_MODEL_SPEL)) {
            return;
        }
        cellWrapper.getCell().setCellValue(cellWrapper.getParser().parseExpression(sub(cellWrapper.getCellValue()))
            .getValue(cellWrapper.getContext(), String.class));
    }
}
