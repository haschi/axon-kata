package org.github.haschi.kata.blueprint.infrastruktur;

import it.kamaladafrica.cdi.axonframework.AutoConfigure;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.eventhandling.scheduling.java.SimpleEventScheduler;
import org.axonframework.eventsourcing.AggregateSnapshotter;
import org.axonframework.eventsourcing.EventCountSnapshotterTrigger;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.SnapshotterTrigger;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.supporting.VolatileEventStore;
import org.axonframework.saga.SagaRepository;
import org.axonframework.saga.repository.inmemory.InMemorySagaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.concurrent.Executors;

@ApplicationScoped
public final class AxonConfiguration {

    @Produces
    @AutoConfigure
    @ApplicationScoped
    public EventBus eventBus() {
        return new SimpleEventBus();
    }

    @Produces
    @AutoConfigure
    @ApplicationScoped
    public EventStore eventStore() {
        return new VolatileEventStore();
    }

    @Produces
    @ApplicationScoped
    public EventScheduler eventScheduler(final EventBus eventBus) {
        return new SimpleEventScheduler(Executors.newSingleThreadScheduledExecutor(), eventBus);
    }

    @Produces
    @AutoConfigure
    @ApplicationScoped
    public SagaRepository sagaRepository() {
        return new InMemorySagaRepository();
    }

    @Produces
    @AutoConfigure
    @ApplicationScoped
    public CommandBus commandBus() {
        return new SimpleCommandBus();
    }

    @Produces
    @ApplicationScoped
    public CommandGateway commandGateway(final CommandBus commandBus) {
        return new DefaultCommandGateway(commandBus);
    }

    // Snapshots

    @Produces
    @AutoConfigure
    @ApplicationScoped
    public Snapshotter snapshotter() {
        return new AggregateSnapshotter();
    }

    @Produces
    @ApplicationScoped
    public SnapshotterTrigger snapshotterTrigger(final Snapshotter snapshotter) {
        final EventCountSnapshotterTrigger trigger = new EventCountSnapshotterTrigger();
        trigger.setSnapshotter(snapshotter);
        return trigger;
    }

}
