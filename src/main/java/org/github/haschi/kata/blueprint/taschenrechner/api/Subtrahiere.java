package org.github.haschi.kata.blueprint.taschenrechner.api;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.github.haschi.kata.blueprint.modellierung.de.Anweisung;
import org.immutables.value.Value;

import java.util.UUID;

@Anweisung
public interface Subtrahiere {
    @TargetAggregateIdentifier
    @Value.Parameter
    UUID taschenrechnerId();
}
