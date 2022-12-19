package com.bloom.bloomspringbootdemo.event.registry;

/**
 * Registry Event Consumer to be used by AbstractRegistry.RegistryEventProcessor
 */
public interface RegistryEventConsumer<E> {

    void onEntryAddedEvent(EntryAddedEvent<E> entryAddedEvent);

    void onEntryRemovedEvent(EntryRemovedEvent<E> entryRemoveEvent);

}
