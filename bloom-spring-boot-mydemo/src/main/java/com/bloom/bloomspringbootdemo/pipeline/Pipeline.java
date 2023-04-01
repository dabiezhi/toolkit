package com.bloom.bloomspringbootdemo.pipeline;


/**
 * @author curry
 * Created by on 2022-08-18 下午5:59
 */
public class Pipeline<T extends BaseContext> {

    private final Handler<T> currentHandler;

    public Pipeline(Handler<T> currentHandler) {
        this.currentHandler = currentHandler;
    }

    public Pipeline<T> addHandler(Handler<T> newHandler) {
        return new Pipeline<>(input -> newHandler.handler(currentHandler.handler(input)));
    }

    public T execute(T input) {
        return currentHandler.handler(input);
    }

}