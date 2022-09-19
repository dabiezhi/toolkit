package com.bloom.bloomspringbootdemo.design.pipeline;

/**
 * @author curry
 * Created by on 2022-08-18 下午5:57
 */
interface Handler<I, O> {
    O process(I input);
}