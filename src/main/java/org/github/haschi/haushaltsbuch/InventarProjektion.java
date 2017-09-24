package org.github.haschi.haushaltsbuch;

import javaslang.collection.Stream;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.config.Configuration;
import org.axonframework.eventsourcing.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;

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

//                .filter(message -> UmlaufvermögenErfasst.class.isAssignableFrom(message.getPayloadType()))
//                .map(message -> (UmlaufvermögenErfasst) message.getPayload())
//                .forEach(ereignis -> builder.addVermoegenswerte(
//                        Vermoegenswert.builder()
//                                .position(ereignis.position())
//                                .währungsbetrag(ereignis.währungsbetrag())
//                                .build()));

        return builder.build();
    }

    private Inventar.Builder inventarErstellen(Inventar.Builder builder, DomainEventMessage message) {

        return Match(message.getPayload()).of(
                Case(ereignis(UmlaufvermögenErfasst.class), h -> builder.addVermoegenswerte(
                        Vermoegenswert.builder().position(h.position()).währungsbetrag(h.währungsbetrag()).build())),
                Case(ereignis(SchuldErfasst.class), h -> builder.addSchulden(
                        Schuld.builder().position(h.position()).währungsbetrag(h.währungsbetrag()).build())),
                Case($(), h -> builder));
    }

    public static <T> Predicate<T> ereignis(final Class<? super T> type)
    {
        return (T obj) -> type.isAssignableFrom(obj.getClass());
    }
}
