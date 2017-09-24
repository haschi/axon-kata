package org.github.haschi.haushaltsbuch;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.config.Configuration;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;

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
        domainEventStream.asStream()
                .filter(message -> UmlaufvermögenErfasst.class.isAssignableFrom(message.getPayloadType()))
                .map(message -> (UmlaufvermögenErfasst) message.getPayload())
                .forEach(ereignis -> builder.addVermoegenswerte(
                        Vermoegenswert.builder()
                                .position(ereignis.position())
                                .währungsbetrag(ereignis.währungsbetrag())
                                .build()));

        return builder.build();
    }
}
