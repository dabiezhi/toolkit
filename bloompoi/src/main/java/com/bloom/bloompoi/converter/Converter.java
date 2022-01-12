/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.bloom.bloompoi.converter;

/**
 * 数据类型转换器接口
 *
 * @author bloom
 * @version V1.0
 * @since 2022-01-06 13:25
 */
public interface Converter<T> {
    /**
     * 将数据从Java对象转换为Excel希望输出的特定字符串
     *
     * @param value 值
     * @return 转换后的值
     */
    String write(T value);

    /**
     * 将Excel读取的字段转换为Java对象类型
     *
     * @param value 值
     * @return 转化后的值
     */
    default T read(String value) {
        return null;
    }
}
