package com.bloom.bloomspringbootdemo.pipeline;

import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * Created by on 2022-10-11 12:05
 */
@Component
public class HelloHandler implements Handler<EsContext> {

    @Override
    public EsContext process(EsContext input) {
        System.out.println("HelloHandler");
        input.setContinueChain(true);
        return input;
    }

}