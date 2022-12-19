package com.bloom.bloomspringbootdemo.event.registry;

import java.time.ZonedDateTime;

abstract class AbstractRegistryEvent implements RegistryEvent {

    private final ZonedDateTime creationTime;

    AbstractRegistryEvent() {
        this.creationTime = ZonedDateTime.now();
    }

    @Override
    public ZonedDateTime getCreationTime() {
        return creationTime;
    }
}