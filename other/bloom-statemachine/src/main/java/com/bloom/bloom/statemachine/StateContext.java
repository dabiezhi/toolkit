package com.bloom.bloom.statemachine;

/**
 * StateContext
 *
 * @author taosy
 * Created by on 2022-05-25 下午5:49
 */
public interface StateContext<S, E, C> {

    /**
     * Gets the transition.
     *
     * @return the transition
     */
    Transition<S, E, C> getTransition();

    /**
     * Gets the state machine.
     *
     * @return the state machine
     */
    StateMachine<S, E, C> getStateMachine();
}
