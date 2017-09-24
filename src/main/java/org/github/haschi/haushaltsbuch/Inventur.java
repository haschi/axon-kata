package org.github.haschi.haushaltsbuch;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.github.haschi.kata.blueprint.modellierung.de.Aggregatkennung;

public class Inventur {

    @AggregateIdentifier
    private Aggregatkennung id;

    public Inventur() {
    }

    @CommandHandler
    public Inventur(BeginneInventur anweisung) {
        AggregateLifecycle.apply(InventurBegonnen.of(anweisung.id()));
    }

    @EventSourcingHandler
    public void falls(InventurBegonnen ereignis) {
        id = ereignis.id();
    }
}
