package com.bloom.rocketmqconsumer.base;

/**
 * @author taosy
 * Created by on 2022-02-09 上午10:20
 */
public interface MessageConsumer<T> {

    void onMessage(T message);

    void start();

    void shutdown();
}