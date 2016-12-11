package org.github.haschi.kata.blueprint.kontext.taschenrechner.api;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.github.haschi.kata.blueprint.modellierung.de.Anweisung;
import org.immutables.value.Value;

import java.util.UUID;

@Anweisung
public interface GebeZahlEin {
    @Value.Parameter
    @TargetAggregateIdentifier
    UUID taschenrechnerId();

    @Value.Parameter
    int zahl();
}
