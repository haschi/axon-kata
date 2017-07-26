package org.github.haschi.kata.blueprint.infrastruktur;

import org.axonframework.config.Configuration;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;

public class Storagelieferant {

    private final EventStorageEngine engine = new InMemoryEventStorageEngine();
    private final EventBus eventBus = new SimpleEventBus();
    private final EmbeddedEventStore eventStore = new EmbeddedEventStore(engine);

    public EventStorageEngine storageEngine() {
        return engine;
    }

    public EventStore eventBus(Configuration konfiguration) {
        return eventStore;
    }
}
