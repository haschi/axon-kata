package org.github.haschi.kata.blueprint.taschenrechner.api;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.github.haschi.kata.blueprint.modellierung.de.Abfrage;
import org.immutables.value.Value;

import java.util.UUID;

@Abfrage
public interface DisplayAblesen {
    @TargetAggregateIdentifier
    @Value.Parameter
    UUID taschenrechnerId();
}
