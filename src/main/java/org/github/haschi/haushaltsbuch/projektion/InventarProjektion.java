package org.github.haschi.haushaltsbuch.projektion;

import javaslang.collection.Stream;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.config.Configuration;
import org.axonframework.eventsourcing.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.github.haschi.haushaltsbuch.api.*;

import java.util.function.Predicate;
import java.util.stream.Collectors;

import static javaslang.API.*;

public class InventarProjektion {

    private final Configuration konfiguration;

    public InventarProjektion(final Configuration konfiguration) {
        this.konfiguration = konfiguration;
    }

    @CommandHandler
    public Inventar zeigeInventar(final ZeigeInventar abfrage) {
        final EventStore eventStore = konfiguration.eventStore();
        final DomainEventStream domainEventStream = eventStore.readEvents(abfrage.id().toString());

        final Inventar.Builder builder = Inventar.builder();
        Stream.ofAll(domainEventStream.asStream().collect(Collectors.toList()))
                .foldLeft(builder, this::inventarErstellen)
                .build();

        return builder.build();
    }

    private Inventar.Builder inventarErstellen(Inventar.Builder builder, DomainEventMessage message) {

        return Match(message.getPayload()).of(
                Case(ereignis(UmlaufvermögenErfasst.class), h -> builder.addVermoegenswerte(vermögenswertErstellen(h))),
                Case(ereignis(SchuldErfasst.class), h -> builder.addSchulden(schuldenErstellen(h))),
                Case($(), h -> builder));
    }

    private Schuld schuldenErstellen(SchuldErfasst h) {
        return Schuld.builder().position(h.position()).währungsbetrag(h.währungsbetrag()).build();
    }

    private Vermoegenswert vermögenswertErstellen(UmlaufvermögenErfasst h) {
        return Vermoegenswert.builder().position(h.position()).währungsbetrag(h.währungsbetrag()).build();
    }

    public static <T> Predicate<T> ereignis(final Class<? super T> type)
    {
        return (T obj) -> type.isAssignableFrom(obj.getClass());
    }
}
