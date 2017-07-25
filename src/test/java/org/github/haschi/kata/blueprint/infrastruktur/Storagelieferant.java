package org.github.haschi.kata.blueprint.infrastruktur;

import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;

public class Storagelieferant {

    private final EventStorageEngine engine = new InMemoryEventStorageEngine();

    public EventStorageEngine storageEngine() {
        return engine;
    }
}
