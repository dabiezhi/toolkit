package com.bloom.bloom.statemachine.builder;

/**
 * StateMachineBuilderFactory
 *
 * @author taosy
 * Created by on 2022-05-25 下午5:55
 */
public class StateMachineBuilderFactory {

    public static <S, E, C> StateMachineBuilder<S, E, C> create() {
        return new StateMachineBuilderImpl<>();
    }
}