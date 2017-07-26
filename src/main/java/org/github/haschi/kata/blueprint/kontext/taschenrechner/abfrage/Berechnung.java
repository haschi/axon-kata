package org.github.haschi.kata.blueprint.kontext.taschenrechner.abfrage;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.config.Configuration;
import org.axonframework.eventhandling.EventHandler;
import org.github.haschi.kata.blueprint.kontext.taschenrechner.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Berechnung {
    private Configuration configuration;
    private UUID taschenrechnerId;

    private List<String> display = new ArrayList<>();

    public Berechnung() {
    }

    @CommandHandler
    public String displayAblesen(final ImmutableDisplayAblesen abfrage) {
        return String.join(" ", display);
    }

    @EventHandler
    public void falls(final ImmutableTaschenrechnerErzeugt ereignis) {
        this.taschenrechnerId = ereignis.id();
    }

    @EventHandler
    public void falls(final ImmutableZahlEingegeben ereignis) {
        display.add(Integer.toString(ereignis.zahl()));
    }

    @EventHandler
    public void falls(final ImmutableErgebnisBerechnet ereignis) {
        display.add(Character.toString(ereignis.operation()));
        display.add("=>");
        display.add(Integer.toString(ereignis.wert()));
    }

    @EventHandler
    public void falls(final ImmutableFehlerAufgetreten ereignis) {
        display.add(Character.toString(ereignis.operation()));
        display.add("=> ERROR");
    }
}
