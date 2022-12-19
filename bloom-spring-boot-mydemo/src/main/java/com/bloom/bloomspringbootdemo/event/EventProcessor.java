package com.bloom.bloomspringbootdemo.event;

import org.springframework.lang.Nullable;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author curry
 * Created by on 2022-12-19 下午3:52
 */
public class EventProcessor<T> implements EventPublisher<T> {

    final List<EventConsumer<T>>                        onEventConsumers = new CopyOnWriteArrayList<>();
    final ConcurrentMap<String, List<EventConsumer<T>>> eventConsumerMap = new ConcurrentHashMap<>();
    private boolean                                     consumerRegistered;

    public boolean hasConsumers() {
        return consumerRegistered;
    }

    @SuppressWarnings("unchecked")
    public synchronized void registerConsumer(String className,
                                              EventConsumer<? extends T> eventConsumer) {
        this.eventConsumerMap.compute(className, (k, consumers) -> {
            if (consumers == null) {
                consumers = new CopyOnWriteArrayList<>();
                consumers.add((EventConsumer<T>) eventConsumer);
                return consumers;
            } else {
                consumers.add((EventConsumer<T>) eventConsumer);
                return consumers;
            }
        });
        this.consumerRegistered = true;
    }

    public <E extends T> boolean processEvent(E event) {
        boolean consumed = false;
        if (!onEventConsumers.isEmpty()) {
            for (EventConsumer<T> onEventConsumer : onEventConsumers) {
                onEventConsumer.consumeEvent(event);
            }
            consumed = true;
        }

        if (!eventConsumerMap.isEmpty()) {
            final List<EventConsumer<T>> consumers = this.eventConsumerMap
                .get(event.getClass().getName());
            if (consumers != null && !consumers.isEmpty()) {
                for (EventConsumer<T> consumer : consumers) {
                    consumer.consumeEvent(event);
                }
                consumed = true;
            }
        }
        return consumed;
    }

    @Override
    public synchronized void onEvent(@Nullable EventConsumer<T> onEventConsumer) {
        this.onEventConsumers.add(onEventConsumer);
        this.consumerRegistered = true;
    }

}