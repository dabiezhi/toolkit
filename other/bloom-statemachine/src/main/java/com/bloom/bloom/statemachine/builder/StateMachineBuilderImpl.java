package com.bloom.bloom.statemachine.builder;

import com.bloom.bloom.statemachine.State;
import com.bloom.bloom.statemachine.StateMachine;
import com.bloom.bloom.statemachine.StateMachineFactory;
import com.bloom.bloom.statemachine.impl.StateMachineImpl;
import com.bloom.bloom.statemachine.impl.TransitionType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * StateMachineBuilderImpl
 *
 * @author taosy
 * Created by on 2022-05-25 下午5:55
 */
public class StateMachineBuilderImpl<S, E, C> implements StateMachineBuilder<S, E, C> {

    private final Map<S, State<S, E, C>> stateMap = new ConcurrentHashMap<>();
    private final StateMachineImpl<S, E, C> stateMachine = new StateMachineImpl<>(stateMap);

    @Override
    public ExternalTransitionBuilder<S, E, C> externalTransition() {
        return new TransitionBuilderImpl<>(stateMap, TransitionType.EXTERNAL);
    }

    @Override
    public ExternalTransitionsBuilder<S, E, C> externalTransitions() {
        return new TransitionsBuilderImpl<>(stateMap, TransitionType.EXTERNAL);
    }

    @Override
    public InternalTransitionBuilder<S, E, C> internalTransition() {
        return new TransitionBuilderImpl<>(stateMap, TransitionType.INTERNAL);
    }

    @Override
    public StateMachine<S, E, C> build(String machineId) {
        stateMachine.setMachineId(machineId);
        stateMachine.setReady(true);
        StateMachineFactory.register(stateMachine);
        return stateMachine;
    }
}