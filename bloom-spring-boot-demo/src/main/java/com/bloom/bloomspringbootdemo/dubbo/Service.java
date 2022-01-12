/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.bloom.bloomspringbootdemo.dubbo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2022 All Rights Reserved.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Inherited
public @interface Service {
    Class<?> interfaceClass() default void.class;

    String interfaceName() default "";

    String version() default "";

    String group() default "";

    String path() default "";

    boolean export() default true;

    String token() default "";

    boolean deprecated() default false;

    boolean dynamic() default true;

    String accesslog() default "";

    int executes() default 0;

    boolean register() default true;

    int weight() default 0;

    String document() default "";

    int delay() default 0;

    /** @deprecated */
    String local() default "";

    String stub() default "";

    String cluster() default "";

    String proxy() default "";

    int connections() default 0;

    int callbacks() default 1;

    String onconnect() default "";

    String ondisconnect() default "";

    String owner() default "";

    String layer() default "";

    int retries() default 2;

    String loadbalance() default "random";

    boolean async() default false;

    int actives() default 0;

    boolean sent() default false;

    String mock() default "";

    String validation() default "";

    int timeout() default 0;

    String cache() default "";

    String[] filter() default {};

    String[] listener() default {};

    String[] parameters() default {};

    String application() default "";

    String module() default "";

    String provider() default "";

    String[] protocol() default {};

    String monitor() default "";

    String[] registry() default {};

    String tag() default "";

}
