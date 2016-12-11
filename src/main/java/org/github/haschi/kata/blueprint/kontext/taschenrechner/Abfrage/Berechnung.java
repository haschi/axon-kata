package org.github.haschi.kata.blueprint.kontext.taschenrechner.Abfrage;


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

import static java.lang.String.format;
import static javaslang.API.*;

public class Berechnung {
    @Inject
    private EventStore eventStore;

    public String abfragen(final UUID taschenrechnerId) {

        final Stream<DomainEventMessage> stream = DomainEventIterator.stream(
                this.eventStore,
                "Taschenrechner",
                taschenrechnerId);

        // U reduce(I, (U, T) -> U, (U, U) -> U)

        final String reduce = stream
                .map(message ->
                        Match(message.getPayload()).of(
                                API.Case(Predicates.instanceOf(ImmutableZahlEingegeben.class), m -> Integer.toString(m.zahl())),
                                API.Case(Predicates.instanceOf(ImmutableErgebnisBerechnet.class), m -> format("%c => %d", m.operation(), m.wert())),
                                Case($(), m -> "")))
                .reduce("", (String left, String right) -> left.isEmpty() ? right : left + " " + right);

        return reduce;
    }

    private class Ansicht {
        StringBuilder stringBuilder;
        String ergebnis;
    }
}
