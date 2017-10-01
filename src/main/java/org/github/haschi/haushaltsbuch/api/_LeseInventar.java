package org.github.haschi.haushaltsbuch.api;

import org.github.haschi.haushaltsbuch.infrastruktur.modellierung.de.Abfrage;
import org.github.haschi.haushaltsbuch.infrastruktur.modellierung.de.Aggregatkennung;

@Abfrage
public interface _LeseInventar {
    Aggregatkennung ausInventur();
}
