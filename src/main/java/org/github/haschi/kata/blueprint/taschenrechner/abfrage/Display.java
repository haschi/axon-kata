package org.github.haschi.kata.blueprint.taschenrechner.abfrage;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.config.Configuration;
import org.axonframework.eventhandling.EventHandler;
import org.github.haschi.kata.blueprint.taschenrechner.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Display {
    private Configuration configuration;
    private UUID taschenrechnerId;

    private List<String> display = new ArrayList<>();

    public Display() {
    }

    @CommandHandler
    public String ablesen(final ImmutableDisplayAblesen abfrage) {
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
