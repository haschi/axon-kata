package org.github.haschi.kata.blueprint.kontext.taschenrechner.abfrage;


import javaslang.API;
import javaslang.Predicates;
import org.axonframework.domain.DomainEventMessage;
import org.axonframework.eventstore.EventStore;
import org.github.haschi.kata.blueprint.infrastruktur.DomainEventIterator;
import org.github.haschi.kata.blueprint.kontext.taschenrechner.api.ImmutableErgebnisBerechnet;
import org.github.haschi.kata.blueprint.kontext.taschenrechner.api.ImmutableZahlEingegeben;

import javax.inject.Inject;
import java.util.UUID;
import java.util.stream.Stream;

import static java.text.MessageFormat.format;
import static javaslang.API.*;

public class Berechnung {

    private final EventStore eventStore;

    @Inject
    public Berechnung(final EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public String abfragen(final UUID taschenrechnerId) {

        final Stream<DomainEventMessage> stream = DomainEventIterator.stream(
                this.eventStore,
                "Taschenrechner",
                taschenrechnerId);

        return stream
                .map(message ->
                        Match(message.getPayload()).of(
                                API.Case(Predicates.instanceOf(ImmutableZahlEingegeben.class), m -> Integer.toString(m.zahl())),
                                API.Case(Predicates.instanceOf(ImmutableErgebnisBerechnet.class), m -> format("{0} => {1,number}", m.operation(), m.wert())),
                                Case($(), m -> "")))
                .reduce("", (String left, String right) -> left.isEmpty() ? right : left + " " + right);
    }
}
