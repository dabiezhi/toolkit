package com.bloom.bloom.statemachine;


import com.bloom.bloom.statemachine.impl.StateMachineException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * StateMachineFactory
 *
 * @author taosy
 * Created by on 2022-05-25 下午5:49
 */
@SuppressWarnings("unchecked")
public class StateMachineFactory {

    static Map<String, StateMachine> stateMachineMap = new ConcurrentHashMap<>();

    private StateMachineFactory() {
    }

    public static <S, E, C> void register(StateMachine<S, E, C> stateMachine) {
        String machineId = stateMachine.getMachineId();
        if (stateMachineMap.get(machineId) != null) {
            throw new StateMachineException("The state machine with id [" + machineId
                                            + "] is already built, no need to build again");
        }
        stateMachineMap.put(stateMachine.getMachineId(), stateMachine);
    }

    public static <S, E, C> StateMachine<S, E, C> get(String machineId) {
        StateMachine<S, E, C> stateMachine = stateMachineMap.get(machineId);
        if (stateMachine == null) {
            throw new StateMachineException(
                "There is no stateMachine instance for " + machineId + ", please build it first");
        }
        return stateMachine;
    }
}
