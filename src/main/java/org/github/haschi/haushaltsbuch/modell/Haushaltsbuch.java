package org.github.haschi.haushaltsbuch.modell;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.github.haschi.haushaltsbuch.api.*;
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

        Inventar inventar = anweisung.inventar();

        Währungsbetrag anlagevermögen = inventar.anlagevermögen().stream()
                .map(v -> v.währungsbetrag())
                .reduce(Währungsbetrag.NullEuro(), (r, l) -> Währungsbetrag.of(r.wert().add(l.wert())));

        Währungsbetrag umlaufvermögen = inventar.umlaufvermögen().stream()
                .map(v -> v.währungsbetrag())
                .reduce(Währungsbetrag.NullEuro(), (r, l) -> Währungsbetrag.of(r.wert().add(l.wert())));

        Währungsbetrag darlehen = inventar.schulden().stream()
                .map(v -> v.währungsbetrag())
                .reduce(Währungsbetrag.NullEuro(), (r, l) -> Währungsbetrag.of(r.wert().add(l.wert())));

        Währungsbetrag eigenkapital = Währungsbetrag.of(
                anlagevermögen.wert()
                        .add(umlaufvermögen.wert())
                        .subtract(darlehen.wert()));

        Eröffnungsbilanzkonto eröffnungsbilanz = Eröffnungsbilanzkonto.builder()
                .addHaben(Buchung.builder()
                        .buchungstext("Anlagevermögen (AV)")
                        .betrag(anlagevermögen)
                        .build())
                .addHaben(Buchung.builder()
                            .buchungstext("Umlaufvermögen (UV)")
                            .betrag(umlaufvermögen)
                        .build())
                .addSoll(Buchung.builder()
                    .buchungstext("Eigenkapital (EK)")
                    .betrag(eigenkapital)
                    .build())
                .addSoll(Buchung.builder()
                            .buchungstext("Fremdkapital (FK)")
                            .betrag(darlehen)
                        .build())
                .build();

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
