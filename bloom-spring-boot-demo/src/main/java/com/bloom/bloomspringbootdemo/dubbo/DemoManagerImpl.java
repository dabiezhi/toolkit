/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2022 All Rights Reserved.
 */
package com.bloom.bloomspringbootdemo.dubbo;

/**
 * @author taosy
 * Created by on 2022-01-12 下午4:41
 */
public class DemoManagerImpl implements DemoManager {

    @Override
    public String demo(String str) {
        return str;
    }
}