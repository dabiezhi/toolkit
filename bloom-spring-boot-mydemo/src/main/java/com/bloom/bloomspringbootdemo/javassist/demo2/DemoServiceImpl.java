package com.bloom.bloomspringbootdemo.javassist.demo2;

import com.alibaba.fastjson.JSON;
import com.bloom.bloomspringbootdemo.valid.User;

import org.springframework.stereotype.Component;

/**
 * @author taosy
 * Created by on 2022-01-12 下午4:41
 */
@Component
public class DemoServiceImpl implements DemoService {

    @Override
    public String demo(String str) {
        return "hello:" + str;
    }

    @Override
    public String user(User user) {
        return JSON.toJSONString(user);
    }
}