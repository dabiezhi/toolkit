package com.bloom.bloomspringbootdemo.design.chain;

import java.util.Objects;

/**
 * @author Administrator
 * Created by on 2022-10-11 9:20
 */
public interface Handler<T extends BaseContext> {

    default T handler(T input) {
        if (Objects.nonNull(input) && !input.isContinueChain()) {
            process(input);
        }
        return input;
    }

    T process(T input);

}