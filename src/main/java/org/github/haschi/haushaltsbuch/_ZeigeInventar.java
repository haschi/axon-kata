package org.github.haschi.haushaltsbuch;

import org.github.haschi.kata.blueprint.modellierung.de.Abfrage;
import org.github.haschi.kata.blueprint.modellierung.de.Aggregatkennung;
import org.immutables.value.Value;

@Abfrage
public interface _ZeigeInventar {
    @Value.Parameter
    Aggregatkennung id();
}
