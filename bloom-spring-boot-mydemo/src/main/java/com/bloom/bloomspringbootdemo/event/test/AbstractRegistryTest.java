package com.bloom.bloomspringbootdemo.event.test;

import com.bloom.bloomspringbootdemo.event.registry.AbstractRegistry;
import com.bloom.bloomspringbootdemo.event.registry.EntryAddedEvent;
import com.bloom.bloomspringbootdemo.event.registry.EntryRemovedEvent;
import com.bloom.bloomspringbootdemo.event.registry.RegistryEventConsumer;

import java.util.Collections;
import java.util.List;

/**
 * @author curry
 * Created by on 2022-12-19 下午4:25
 */
public class AbstractRegistryTest {

    public static void main(String[] args) {

        RegistryEventConsumer<String> registryEventConsumer = new RegistryEventConsumer<String>() {
            @Override
            public void onEntryAddedEvent(EntryAddedEvent<String> entryAddedEvent) {
                System.out.println("onEntryAddedEvent");
            }

            @Override
            public void onEntryRemovedEvent(EntryRemovedEvent<String> entryRemoveEvent) {
            }
        };

        TestRegistry testRegistry = new TestRegistry(
            Collections.singletonList(registryEventConsumer));
        String addedEntry1 = testRegistry.computeIfAbsent("name", () -> "entry1");
        System.out.println(addedEntry1);
    }

    static class TestRegistry extends AbstractRegistry<String, String> {

        TestRegistry(List<RegistryEventConsumer<String>> registryEventConsumer) {
            super(registryEventConsumer);
        }
    }
}