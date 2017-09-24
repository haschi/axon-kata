package org.github.haschi.haushaltsbuch;

import org.github.haschi.kata.blueprint.modellierung.de.Information;

@Information
public abstract class _Inventar {
    public static Inventar leer() {
        return Inventar.builder().build();
    }
}
