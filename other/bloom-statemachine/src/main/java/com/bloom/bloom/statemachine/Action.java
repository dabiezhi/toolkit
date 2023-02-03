package com.bloom.bloom.statemachine;

/**
 * Action
 *
 * @author taosy
 * Created by on 2022-05-25 下午5:35
 */
public interface Action<S, E, C> {

    void execute(S from, S to, E event, C context);
}