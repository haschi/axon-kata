package org.github.haschi.haushaltsbuch.api;

import org.github.haschi.kata.blueprint.modellierung.de.Aggregatkennung;
import org.github.haschi.kata.blueprint.modellierung.de.Ereignis;
import org.immutables.value.Value;

@Ereignis
public interface _InventurBegonnen {
    @Value.Parameter
    Aggregatkennung id();
}
