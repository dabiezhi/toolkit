package com.bloom.bloom.statemachine.builder;

/**
 * @author taosy
 * Created by on 2022-05-25 下午5:46
 */
public interface To<S, E, C> {

    On<S, E, C> on(E event);
}