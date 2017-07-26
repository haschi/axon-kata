package org.github.haschi.kata.blueprint.taschenrechner.api;

import org.github.haschi.kata.blueprint.modellierung.de.Ereignis;
import org.immutables.value.Value;

import java.util.UUID;

@Ereignis
public interface ZahlEingegeben {
    @Value.Parameter
    UUID taschenrechnerId();

    @Value.Parameter
    int zahl();
}
