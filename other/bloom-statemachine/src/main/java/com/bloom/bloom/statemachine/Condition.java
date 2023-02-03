package com.bloom.bloom.statemachine;

/**
 * Condition
 * 
 * @author taosy
 * Created by on 2022-05-25 下午5:49
 */
public interface Condition<C> {

    boolean isSatisfied(C context);
}