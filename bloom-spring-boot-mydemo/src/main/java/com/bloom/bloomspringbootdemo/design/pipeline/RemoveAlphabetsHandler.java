package com.bloom.bloomspringbootdemo.design.pipeline;

/**
 * @author curry
 * Created by on 2022-08-18 下午5:58
 */
public class RemoveAlphabetsHandler implements Handler<String, String> {

    @Override
    public String process(String input) {
        System.out.println("RemoveAlphabetsHandler===>");
        return "123";
    }
}