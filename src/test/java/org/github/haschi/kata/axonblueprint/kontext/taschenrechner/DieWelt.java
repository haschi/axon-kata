package org.github.haschi.kata.axonblueprint.kontext.taschenrechner;

import org.axonframework.domain.DomainEventMessage;
import org.axonframework.domain.EventContainer;
import org.axonframework.domain.MetaData;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventstore.EventStore;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class DieWelt {

    @Inject
    private EventStore eventStore;

    private final Deque ereignisse = new LinkedList<>();

    private final Map<Object, EventContainer> eventContainers = new ConcurrentHashMap<>();

    public UUID taschenrechnerId;
    public String berechnung;

    public Object letztesEreignis() {
        return this.ereignisse.peek();
    }

    @EventHandler
    public void falls(final DomainEventMessage<?> ereignis) {
        this.ereignisse.push(ereignis.getPayload());
    }

    public void speichern(final Object aggregatId, final Class<?> aggregateRoot, final Object... ereignisse) {
        // stellt Aggregat her
        final EventContainer eventContainer = Optional.ofNullable(this.eventContainers.get(aggregatId)).orElseGet(() -> {
            final EventContainer ec = new EventContainer(aggregatId);
            ec.initializeSequenceNumber(0L);
            this.eventContainers.put(aggregatId, ec);
            return ec;
        });

        Arrays.stream(ereignisse).forEach(e -> eventContainer.addEvent(MetaData.emptyInstance(), e));
        this.eventStore.appendEvents(aggregateRoot.getSimpleName(), eventContainer.getEventStream());
        eventContainer.commit();
    }
}
