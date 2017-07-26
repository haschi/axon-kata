package org.github.haschi.kata.blueprint.kontext.taschenrechner.domaene;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.github.haschi.kata.blueprint.kontext.taschenrechner.api.*;

import java.util.LinkedList;
import java.util.UUID;
import java.util.function.BiFunction;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

public class Taschenrechner {

    @AggregateIdentifier
    private UUID id;

    private LinkedList<Integer> stack;

    private Taschenrechner() {
    }

    @CommandHandler
    public Taschenrechner(final ImmutableErzeugeTaschenrechner ereignis) {
        apply(ImmutableTaschenrechnerErzeugt.of(ereignis.id()));
    }

    @EventSourcingHandler
    public void falls(final ImmutableTaschenrechnerErzeugt ereignis) {
        this.stack = new LinkedList<>();
        this.id = ereignis.id();
    }

    @CommandHandler
    public void zahlEingeben(final ImmutableGebeZahlEin anweisung) {
        apply(ImmutableZahlEingegeben.of(this.id, anweisung.zahl()));
    }

    @EventSourcingHandler
    public void falls(final ImmutableZahlEingegeben ereignis) {
        this.stack.addFirst(ereignis.zahl());
    }

    @CommandHandler
    public void addieren(final ImmutableAddiere anweisung) {

        operieren('+', (Integer left, Integer right) -> left + right);
    }

    private void operieren(final char operator, final BiFunction<Integer, Integer, Integer> f) {
        if (this.stack.size() >= 2) {
            apply(ImmutableErgebnisBerechnet.of(this.id, f.apply(this.stack.get(1), this.stack.get(0)), operator));
        } else {
            apply(ImmutableFehlerAufgetreten.of(this.id, operator));
        }
    }

    @CommandHandler
    public void subtrahieren(final ImmutableSubtrahiere anweisung) {
        operieren('-', (Integer links, Integer rechts) -> links - rechts);
    }

    @CommandHandler
    public void dividiere(final ImmutableDividiere anweisung) {
        operieren('/', (Integer links, Integer rechts) -> links / rechts);
    }

    @CommandHandler
    public void multiplizieren(final ImmutableMultipliziere anweisung) {
        operieren('*', (Integer links, Integer rechts) -> links * rechts);
    }

    @EventSourcingHandler
    public void falls(final ImmutableErgebnisBerechnet ereignis) {
        this.stack.removeFirst();
        this.stack.removeFirst();
        this.stack.addFirst(ereignis.wert());
    }
}
