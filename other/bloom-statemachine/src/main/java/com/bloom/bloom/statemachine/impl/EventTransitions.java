package com.bloom.bloom.statemachine.impl;


import com.bloom.bloom.statemachine.Transition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * EventTransitions
 *
 * 同一个Event可以触发多个Transitions，https://github.com/alibaba/COLA/pull/158
 *
 * @author taosy
 * Created by on 2022-05-25 下午5:51
 */
public class EventTransitions<S, E, C> {

    private final HashMap<E, List<Transition<S, E, C>>> eventTransitions;

    public EventTransitions() {
        eventTransitions = new HashMap<>();
    }

    public void put(E event, Transition<S, E, C> transition) {
        if (eventTransitions.get(event) == null) {
            List<Transition<S, E, C>> transitions = new ArrayList<>();
            transitions.add(transition);
            eventTransitions.put(event, transitions);
        } else {
            List<Transition<S, E, C>> existingTransitions = eventTransitions.get(event);
            verify(existingTransitions, transition);
            existingTransitions.add(transition);
        }
    }

    private void verify(List<Transition<S, E, C>> existingTransitions,
                        Transition<S, E, C> newTransition) {
        for (Transition<S, E, C> transition : existingTransitions) {
            if (transition.getCondition().equals(newTransition.getEvent())
                && transition.getSource().equals(newTransition.getSource())
                && transition.getTarget().equals(transition.getTarget())) {
                throw new StateMachineException(
                    transition + " already Exist, you can not add another one");
            }
        }
    }

    public List<Transition<S, E, C>> get(E event) {
        return eventTransitions.get(event);
    }

    public List<Transition<S, E, C>> allTransitions() {
        List<Transition<S, E, C>> allTransitions = new ArrayList<>();
        for (List<Transition<S, E, C>> transitions : eventTransitions.values()) {
            allTransitions.addAll(transitions);
        }
        return allTransitions;
    }
}
