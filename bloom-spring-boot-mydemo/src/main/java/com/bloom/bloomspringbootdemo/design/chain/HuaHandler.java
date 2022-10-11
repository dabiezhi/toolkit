package com.bloom.bloomspringbootdemo.design.chain;

import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * Created by on 2022-10-11 12:05
 */
@Component
public class HuaHandler implements Handler<EsContext> {

    @Override
    public EsContext process(EsContext input) {
        System.out.println("HuaHandler");
        return input;
    }
}