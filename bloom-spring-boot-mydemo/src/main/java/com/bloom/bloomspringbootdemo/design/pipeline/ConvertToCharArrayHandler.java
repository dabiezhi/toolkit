package com.bloom.bloomspringbootdemo.design.pipeline;

/**
 * @author curry
 * Created by on 2022-08-18 下午5:58
 */
public class ConvertToCharArrayHandler implements Handler<String, Integer> {

    @Override
    public Integer process(String input) {
        System.out.println("ConvertToCharArrayHandler===>");
        return 1234567;
    }
}