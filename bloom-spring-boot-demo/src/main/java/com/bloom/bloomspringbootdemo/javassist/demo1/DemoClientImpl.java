/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2022 All Rights Reserved.
 */
package com.bloom.bloomspringbootdemo.javassist.demo1;

import com.bloom.bloomspringbootdemo.javassist.anno.FeignClientService;

/**
 * @author taosy
 * Created by on 2022-01-18 下午2:31
 */
@FeignClientService(feignClass = DemoFegin.class, clientClass = DemoClient.class)
public interface DemoClientImpl extends DemoClient {

}