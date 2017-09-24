package org.github.haschi.haushaltsbuch.api;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.github.haschi.kata.blueprint.modellierung.de.Aggregatkennung;
import org.github.haschi.kata.blueprint.modellierung.de.Anweisung;
import org.immutables.value.Value;

@Anweisung
public interface  _BeginneInventur {
    @TargetAggregateIdentifier
    @Value.Parameter
    Aggregatkennung id();
}