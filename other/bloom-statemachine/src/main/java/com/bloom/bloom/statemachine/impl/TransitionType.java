package com.bloom.bloom.statemachine.impl;

/**
 * TransitionType
 *
 * @author taosy
 * Created by on 2022-05-25 下午5:51
 */
public enum TransitionType {
                            /**
                             * Implies that the Transition, if triggered, occurs without exiting or entering the source State
                             * (i.e., it does not cause a state change). This means that the entry or exit condition of the source
                             * State will not be invoked. An internal Transition can be taken even if the SateMachine is in one or
                             * more Regions nested within the associated State.
                             */
                            INTERNAL,
                            /**
                             * Implies that the Transition, if triggered, will exit the composite (source) State.
                             */
                            EXTERNAL
}
