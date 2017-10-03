package org.github.haschi.haushaltsbuch.api;

import org.github.haschi.haushaltsbuch.infrastruktur.modellierung.de.Abfrage;
import org.github.haschi.haushaltsbuch.infrastruktur.modellierung.de.Aggregatkennung;
import org.immutables.value.Value;

@Abfrage
public interface _LeseInventar
{
    @Value.Parameter
    Aggregatkennung ausInventur();
}
