package com.bloom.bloom.statemachine.impl;


import com.bloom.bloom.statemachine.State;

import java.util.Map;

/**
 * StateHelper
 *
 * @author taosy
 * Created by on 2022-05-25 下午5:51
 */
public class StateHelper {

    public static <S, E, C> State<S, E, C> getState(Map<S, State<S, E, C>> stateMap, S stateId) {
        State<S, E, C> state = stateMap.get(stateId);
        if (state == null) {
            state = new StateImpl<>(stateId);
            stateMap.put(stateId, state);
        }
        return state;
    }
}
