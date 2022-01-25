package com.bloom.bloomspringbootdemo.javassist.demo2;

import com.bloom.bloomspringbootdemo.javassist.anno.ResultService;

/**
 * @author taosy
 * Created by on 2022-01-12 下午4:41
 */
@ResultService(serviceClass = DemoService.class, facadeClass = DemoFacade.class)
public interface DemoController extends DemoFacade {

}