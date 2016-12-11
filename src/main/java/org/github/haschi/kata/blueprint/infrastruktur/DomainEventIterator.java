package org.github.haschi.kata.blueprint.infrastruktur;

import org.axonframework.domain.DomainEventMessage;
import org.axonframework.domain.DomainEventStream;
import org.axonframework.eventstore.EventStore;

import java.util.Iterator;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Kapselt einen DomainEventStream und macht diesen als Iterator oder Stream
 * verfügbar.
 */
public class DomainEventIterator implements Iterator<DomainEventMessage> {
    private final DomainEventStream stream;

    public DomainEventIterator(final DomainEventStream stream) {

        this.stream = stream;
    }

    public static DomainEventIterator of(final DomainEventStream stream) {
        return new DomainEventIterator(stream);
    }

    public static Stream<DomainEventMessage> stream(final EventStore eventStore, final String aggregat, final UUID taschenrechnerId) {
        final DomainEventStream stream = eventStore.readEvents(aggregat, taschenrechnerId);
        final DomainEventIterator iterator = of(stream);

        return iterator.stream();
    }

    @Override
    public boolean hasNext() {
        return this.stream.hasNext();

    }

    @Override
    public DomainEventMessage next() {
        final DomainEventMessage message = this.stream.peek();
        this.stream.next();

        return message;
    }

    public Stream<DomainEventMessage> stream() {
        final Iterable<DomainEventMessage> iterable = () -> this;
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
