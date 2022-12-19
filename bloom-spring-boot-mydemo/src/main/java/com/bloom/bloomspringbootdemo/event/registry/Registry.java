/*
 *
 *  Copyright 2019 Mahmoud Romeh, Robert Winkler
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */
package com.bloom.bloomspringbootdemo.event.registry;

import com.bloom.bloomspringbootdemo.event.EventConsumer;

import java.util.Optional;

/**
 * root resilience4j registry to be used by resilience types registries for common functionality
 */
public interface Registry<E, C> {

    /**
     * Find a named entry in the Registry
     *
     * @param name the  name
     */
    Optional<E> find(String name);

    /**
     * Remove an entry from the Registry
     *
     * @param name the  name
     */
    Optional<E> remove(String name);

    /**
     * Returns an EventPublisher which can be used to register event consumers.
     *
     * @return an EventPublisher
     */
    EventPublisher<E> getEventPublisher();

    /**
     * An EventPublisher can be used to register event consumers.
     */
    interface EventPublisher<E> extends
                            com.bloom.bloomspringbootdemo.event.EventPublisher<RegistryEvent> {

        EventPublisher<E> onEntryAdded(EventConsumer<EntryAddedEvent<E>> eventConsumer);

        EventPublisher<E> onEntryRemoved(EventConsumer<EntryRemovedEvent<E>> eventConsumer);

    }
}
