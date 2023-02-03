package com.bloom.bloom.statemachine;


import com.bloom.bloom.statemachine.impl.TransitionType;

import java.util.Collection;
import java.util.List;


/**
 * State
 *
 * @param <S> the type of state
 * @param <E> the type of event
 * @author taosy
 * Created by on 2022-05-25 下午5:49
 */
public interface State<S, E, C> {

    /**
     * Gets the state identifier.
     *
     * @return the state identifiers
     */
    S getId();

    Transition<S, E, C> addTransition(E event, State<S, E, C> target,
                                      TransitionType transitionType);

    List<Transition<S, E, C>> getEventTransitions(E event);

    Collection<Transition<S, E, C>> getAllTransitions();

}
