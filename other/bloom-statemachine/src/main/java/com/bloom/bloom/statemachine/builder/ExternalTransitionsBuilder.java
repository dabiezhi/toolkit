package com.bloom.bloom.statemachine.builder;

/**
 * ExternalTransitionsBuilder
 * This builder is for multiple transitions, currently only support multiple sources <----> one target
 *
 * @author taosy
 * Created by on 2022-05-25 下午5:45
 */
public interface ExternalTransitionsBuilder<S, E, C> {

    From<S, E, C> fromAmong(S... stateIds);
}
