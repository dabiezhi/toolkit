package com.bloom.bloom.statemachine.builder;

/**
 * ExternalTransitionBuilder
 *
 * @author taosy
 * Created by on 2022-05-25 下午5:45
 */
public interface ExternalTransitionBuilder<S, E, C> {

    From<S, E, C> from(S stateId);
}