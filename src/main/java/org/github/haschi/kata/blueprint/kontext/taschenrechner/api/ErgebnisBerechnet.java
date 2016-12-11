package org.github.haschi.kata.blueprint.kontext.taschenrechner.api;

import org.github.haschi.kata.blueprint.modellierung.de.Ereignis;
import org.immutables.value.Value;

import java.util.UUID;

@Ereignis
public interface ErgebnisBerechnet {
    @Value.Parameter
    UUID taschenrechnerId();

    @Value.Parameter
    int wert();

    @Value.Parameter
    char operation();
}
