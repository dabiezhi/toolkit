/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.bloom.bloomspringbootdemo.dubbo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2022 All Rights Reserved.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface Name {
    String value();
}
