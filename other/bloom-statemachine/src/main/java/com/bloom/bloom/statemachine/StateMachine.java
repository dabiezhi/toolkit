package com.bloom.bloom.statemachine;

/**
 * StateMachine
 *
 * @author taosy
 * Created by on 2022-05-25 下午5:49
 */
public interface StateMachine<S, E, C> {

    /**
     * Send an event {@code E} to the state machine.
     *
     * @param sourceState the source state
     * @param event the event to send
     * @param ctx the user defined context
     * @return the target state
     */
    S fireEvent(S sourceState, E event, C ctx);

    /**
     * MachineId is the identifier for a State Machine
     * @return
     */
    String getMachineId();

}
