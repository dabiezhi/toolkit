package com.bloom.bloomspringbootdemo.event.registry;

import com.bloom.bloomspringbootdemo.event.EventConsumer;
import com.bloom.bloomspringbootdemo.event.EventProcessor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Abstract registry to be shared with all resilience4j registries
 */
public class AbstractRegistry<E, C> implements Registry<E, C> {

    protected static final String        DEFAULT_CONFIG                  = "default";
    protected static final String        CONFIG_MUST_NOT_BE_NULL         = "Config must not be null";
    protected static final String        CONSUMER_MUST_NOT_BE_NULL       = "EventConsumers must not be null";
    protected static final String        SUPPLIER_MUST_NOT_BE_NULL       = "Supplier must not be null";
    protected static final String        TAGS_MUST_NOT_BE_NULL           = "Tags must not be null";
    private static final String          NAME_MUST_NOT_BE_NULL           = "Name must not be null";
    private static final String          REGISTRY_STORE_MUST_NOT_BE_NULL = "Registry Store must not be null";

    protected final RegistryStore<E>     entryMap;

    private final RegistryEventProcessor eventProcessor;

    public AbstractRegistry(List<RegistryEventConsumer<E>> registryEventConsumers) {
        this.entryMap = new InMemoryRegistryStore<E>();
        this.eventProcessor = new RegistryEventProcessor(
            Objects.requireNonNull(registryEventConsumers, CONSUMER_MUST_NOT_BE_NULL));
    }

    public AbstractRegistry(List<RegistryEventConsumer<E>> registryEventConsumers,
                            RegistryStore<E> registryStore) {
        this.entryMap = Objects.requireNonNull(registryStore, REGISTRY_STORE_MUST_NOT_BE_NULL);
        this.eventProcessor = new RegistryEventProcessor(
            Objects.requireNonNull(registryEventConsumers, CONSUMER_MUST_NOT_BE_NULL));
    }

    public E computeIfAbsent(String name, Supplier<E> supplier) {
        return entryMap.computeIfAbsent(Objects.requireNonNull(name, NAME_MUST_NOT_BE_NULL), k -> {
            E entry = supplier.get();
            eventProcessor.processEvent(new EntryAddedEvent<>(entry));
            return entry;
        });
    }

    @Override
    public Optional<E> find(String name) {
        return entryMap.find(name);
    }

    @Override
    public Optional<E> remove(String name) {
        Optional<E> removedEntry = entryMap.remove(name);
        removedEntry
            .ifPresent(entry -> eventProcessor.processEvent(new EntryRemovedEvent<>(entry)));
        return removedEntry;
    }

    @Override
    public EventPublisher<E> getEventPublisher() {
        return eventProcessor;
    }

    private class RegistryEventProcessor extends EventProcessor<RegistryEvent> implements
                                         EventConsumer<RegistryEvent>, EventPublisher<E> {

        private RegistryEventProcessor() {
        }

        private RegistryEventProcessor(List<RegistryEventConsumer<E>> registryEventConsumers) {
            registryEventConsumers.forEach(consumer -> {
                onEntryAdded(consumer::onEntryAddedEvent);
                onEntryRemoved(consumer::onEntryRemovedEvent);
            });
        }

        @Override
        public EventPublisher<E> onEntryAdded(EventConsumer<EntryAddedEvent<E>> onSuccessEventConsumer) {
            registerConsumer(EntryAddedEvent.class.getName(), onSuccessEventConsumer);
            return this;
        }

        @Override
        public EventPublisher<E> onEntryRemoved(EventConsumer<EntryRemovedEvent<E>> onErrorEventConsumer) {
            registerConsumer(EntryRemovedEvent.class.getName(), onErrorEventConsumer);
            return this;
        }

        @Override
        public void consumeEvent(RegistryEvent event) {
            super.processEvent(event);
        }
    }

}
