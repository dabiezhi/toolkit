package com.bloom.bloom.statemachine.builder;

/**
 * From
 *
 * @author taosy
 * Created by on 2022-05-25 下午5:46
 */
public interface From<S, E, C> {

    To<S, E, C> to(S stateId);
}