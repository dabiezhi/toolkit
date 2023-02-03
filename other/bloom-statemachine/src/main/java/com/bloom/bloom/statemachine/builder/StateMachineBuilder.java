package com.bloom.bloom.statemachine.builder;


import com.bloom.bloom.statemachine.StateMachine;

/**
 * StateMachineBuilder
 *
 * @param <S> the type of state
 * @param <E> the type of event
 * @param <C> the type of user defined context, which is used to hold application data
 * @author taosy
 * Created by on 2022-05-25 下午5:45
 */
public interface StateMachineBuilder<S, E, C> {

    ExternalTransitionBuilder<S, E, C> externalTransition();

    ExternalTransitionsBuilder<S, E, C> externalTransitions();

    InternalTransitionBuilder<S, E, C> internalTransition();

    StateMachine<S, E, C> build(String machineId);
}