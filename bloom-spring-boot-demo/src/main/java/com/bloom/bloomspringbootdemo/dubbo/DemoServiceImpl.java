/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2022 All Rights Reserved.
 */
package com.bloom.bloomspringbootdemo.dubbo;

/**
 * @author taosy
 * Created by on 2022-01-12 下午4:41
 */
@ResultService(managerClass = DemoManager.class, dubboService = @Service(interfaceClass = DemoService.class))
public interface DemoServiceImpl extends DemoService {

}