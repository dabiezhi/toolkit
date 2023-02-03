package com.bloom.bloom.statemachine.builder;

/**
 * InternalTransitionBuilder
 *
 * @author taosy
 * Created by on 2022-05-25 下午5:46
 */
public interface InternalTransitionBuilder<S, E, C> {

    /**
     * Build a internal transition
     * @param stateId id of transition
     * @return To clause builder
     */
    To<S, E, C> within(S stateId);
}
