/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.bloom.bloompoi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 模型excel特殊字段绑定(针对水平顺序读取的字段)
 * 例: name:bloom address:hangzhou *
 *
 * @author bloom
 * @version V1.0
 * @since 2022-01-06 13:28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Special {

    /**
     * 是否为特殊字段,默认false
     *
     * @return true/false
     */
    boolean isSpecial() default false;

    /**
     * 特殊字段列号
     *
     * @return 列号
     */
    int specialColNum() default -1;

    /**
     * 特殊字段行号
     *
     * @return 行号
     */
    int specialRowNum() default -1;
}
