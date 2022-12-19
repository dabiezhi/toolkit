package com.bloom.bloomspringbootdemo.event;

/**
 * @author curry
 * Created by on 2022-12-19 下午3:50
 */
public interface EventPublisher<T> {

    void onEvent(EventConsumer<T> onEventConsumer);
}