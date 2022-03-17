/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.bloom.bloompoi.writer.spel.strategy;

import com.bloom.bloompoi.Constant;
import com.bloom.bloompoi.core.utils.StringUtil;
import com.bloom.bloompoi.writer.CellWrapper;

import org.apache.poi.ss.usermodel.Cell;

/**
 * 针对不同的标识对SpEl进行不同的策略实现
 *
 * @author bloom
 * @version V1.0
 * @since 2022-01-06 22:56
 */
public interface CellSpElStrategy {

    /**
     * 处理cell中的SpEl
     *
     * @param cellWrapper 列包装对象
     */
    void handle(CellWrapper cellWrapper);

    /**
     * 对SpEL的#符号进行substring操作
     *
     * @param value 列的值
     * @return substring操作后的值
     */
    default String sub(String value) {
        if (StringUtil.isBlank(value)) {
            return Constant.EMPTY_STRING;
        }
        return value.substring(value.indexOf(Constant.POUND));
    }

    /**
     * 对SpEL的()符号进行substring操作
     *
     * @param value 列的值
     * @return substring操作后的值
     */
    default String subBracket(String value) {
        if (StringUtil.isBlank(value)) {
            return Constant.EMPTY_STRING;
        }
        return value.substring(value.indexOf(Constant.LEFT_BRACKET) + 1, value.indexOf(Constant.RIGHT_BRACKET));
    }
}
