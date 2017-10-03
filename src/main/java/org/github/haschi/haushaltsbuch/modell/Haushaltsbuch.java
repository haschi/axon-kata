package org.github.haschi.haushaltsbuch.modell;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.github.haschi.haushaltsbuch.api.BeginneHaushaltsbuchführung;
import org.github.haschi.haushaltsbuch.api.Eröffnungsbilanzkonto;
import org.github.haschi.haushaltsbuch.api.EröffnungsbilanzkontoErstellt;
import org.github.haschi.haushaltsbuch.api.HaushaltsbuchführungBegonnen;
import org.github.haschi.haushaltsbuch.infrastruktur.modellierung.de.Aggregatkennung;

public class Haushaltsbuch {

    @AggregateIdentifier
    private Aggregatkennung id;

    public Haushaltsbuch() {}

    @CommandHandler
    public Haushaltsbuch(final BeginneHaushaltsbuchführung anweisung) {
        AggregateLifecycle.apply(
                HaushaltsbuchführungBegonnen.builder()
                        .id(anweisung.id())
                        .build());

        Eröffnungsbilanzkonto eröffnungsbilanz = Eröffnungsbilanzkonto.builder().build();

        AggregateLifecycle.apply(
                EröffnungsbilanzkontoErstellt.builder()
                .eröffnungsbilanzkonto(eröffnungsbilanz)
                .build());
    }

    @EventSourcingHandler
    public void falls(final HaushaltsbuchführungBegonnen ereignis) {
        id = ereignis.id();
    }
}
