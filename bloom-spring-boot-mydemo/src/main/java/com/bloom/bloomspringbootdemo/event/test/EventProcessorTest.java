package com.bloom.bloomspringbootdemo.event.test;

import com.bloom.bloomspringbootdemo.event.EventConsumer;
import com.bloom.bloomspringbootdemo.event.EventProcessor;

/**
 * @author curry
 * Created by on 2022-12-19 下午3:52
 */
public class EventProcessorTest {
    public static void main(String[] args) {
        EventProcessor<Number> eventProcessor = new EventProcessor<>();
        EventConsumer<Number> eventConsumer = event -> System.out.println(event.toString());

        eventProcessor.onEvent(eventConsumer);
        eventProcessor.onEvent(eventConsumer);

        boolean consumed = eventProcessor.processEvent(1);
    }
}