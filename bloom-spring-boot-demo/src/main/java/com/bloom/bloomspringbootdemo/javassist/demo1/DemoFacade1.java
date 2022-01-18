/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2022 All Rights Reserved.
 */
package com.bloom.bloomspringbootdemo.javassist.demo1;

import com.bloom.bloomspringbootdemo.valid.BaseResult;

/**
 * @author taosy
 * Created by on 2022-01-12 下午4:41
 */
public interface DemoFacade1 {

    BaseResult<String> demo(String userId);

}