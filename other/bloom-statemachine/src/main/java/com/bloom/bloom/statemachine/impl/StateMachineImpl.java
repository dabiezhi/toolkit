package com.bloom.bloom.statemachine.impl;

import com.bloom.bloom.statemachine.State;
import com.bloom.bloom.statemachine.StateMachine;
import com.bloom.bloom.statemachine.Transition;

import java.util.List;
import java.util.Map;

/**
 * StateMachineImpl
 *
 * For performance consideration,
 * The state machine is made "stateless" on purpose.
 * Once it's built, it can be shared by multi-thread
 * <p>
 * One side effect is since the state machine is stateless, we can not get current state from State Machine.
 *
 * @author taosy
 * Created by on 2022-05-25 下午5:51
 */
public class StateMachineImpl<S, E, C> implements StateMachine<S, E, C> {

    private String                       machineId;

    private final Map<S, State<S, E, C>> stateMap;

    private boolean                      ready;

    public StateMachineImpl(Map<S, State<S, E, C>> stateMap) {
        this.stateMap = stateMap;
    }

    @Override
    public S fireEvent(S sourceStateId, E event, C ctx) {
        isReady();
        Transition<S, E, C> transition = routeTransition(sourceStateId, event, ctx);

        if (transition == null) {
            return sourceStateId;
        }

        return transition.transit(ctx, false).getId();
    }

    private Transition<S, E, C> routeTransition(S sourceStateId, E event, C ctx) {
        State<S, E, C> sourceState = getState(sourceStateId);

        List<Transition<S, E, C>> transitions = sourceState.getEventTransitions(event);

        if (transitions == null || transitions.size() == 0) {
            return null;
        }

        Transition<S, E, C> transit = null;
        for (Transition<S, E, C> transition : transitions) {
            if (transition.getCondition() == null) {
                transit = transition;
            } else if (transition.getCondition().isSatisfied(ctx)) {
                transit = transition;
                break;
            }
        }

        return transit;
    }

    private State<S, E, C> getState(S currentStateId) {
        return StateHelper.getState(stateMap, currentStateId);
    }

    private void isReady() {
        if (!ready) {
            throw new StateMachineException("State machine is not built yet, can not work");
        }
    }

    @Override
    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
}
