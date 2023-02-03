package com.bloom.bloom.statemachine.builder;


import com.bloom.bloom.statemachine.Action;

/**
 * When
 *
 * @author taosy
 * Created by on 2022-05-25 下午5:48
 */
public interface When<S, E, C> {

    void perform(Action<S, E, C> action);
}