package com.bloom.bloom.statemachine.builder;

import com.bloom.bloom.statemachine.Condition;

/**
 * On
 *
 * @author taosy
 * Created by on 2022-05-25 下午5:46
 */
public interface On<S, E, C> extends When<S, E, C> {

    When<S, E, C> when(Condition<C> condition);
}