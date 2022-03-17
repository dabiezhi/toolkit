/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.bloom.bloompoi.exception;

/**
 * 自定义异常类
 *
 * @author bloom
 * @version V1.0
 * @since 2022-01-06 13:34
 */
public class ExcelException extends RuntimeException {

    public ExcelException(String message) {
        super(message);
    }

    public ExcelException(Throwable cause, String message) {
        super(message, cause);
    }

    public ExcelException(Throwable cause) {
        super(cause);
    }

}
