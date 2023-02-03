package com.bloom.bloom.statemachine.impl;

import com.bloom.bloom.statemachine.State;
import com.bloom.bloom.statemachine.Transition;

import java.util.Collection;
import java.util.List;

/**
 * StateImpl
 *
 * @author taosy
 * Created by on 2022-05-25 下午5:51
 */
public class StateImpl<S, E, C> implements State<S, E, C> {

    protected final S                       stateId;
    private final EventTransitions<S, E, C> eventTransitions = new EventTransitions<>();

    StateImpl(S stateId) {
        this.stateId = stateId;
    }

    @Override
    public Transition<S, E, C> addTransition(E event, State<S, E, C> target,
                                             TransitionType transitionType) {
        Transition<S, E, C> newTransition = new TransitionImpl<>();
        newTransition.setSource(this);
        newTransition.setTarget(target);
        newTransition.setEvent(event);
        newTransition.setType(transitionType);

        eventTransitions.put(event, newTransition);
        return newTransition;
    }

    @Override
    public List<Transition<S, E, C>> getEventTransitions(E event) {
        return eventTransitions.get(event);
    }

    @Override
    public Collection<Transition<S, E, C>> getAllTransitions() {
        return eventTransitions.allTransitions();
    }

    @Override
    public S getId() {
        return stateId;
    }

}
