package com.bloom.bloomspringbootdemo.javassist.demo1;

import com.bloom.bloomspringbootdemo.valid.BaseResult;

import org.springframework.stereotype.Component;

/**
 * @author taosy
 * Created by on 2022-01-18 下午2:32
 */
@Component
public class DemoFeginImpl implements DemoFegin {

    @Override
    public BaseResult<String> demo(String userId) {
        return BaseResult.success("你好呀");
    }
}