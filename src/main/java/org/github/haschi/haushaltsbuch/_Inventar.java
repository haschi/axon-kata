package org.github.haschi.haushaltsbuch;

import org.github.haschi.kata.blueprint.modellierung.de.Information;

import java.util.List;

@Information
public abstract class _Inventar {
    public static Inventar leer() {
        return Inventar.builder().build();
    }

    public abstract List<Vermoegenswert> vermoegenswerte();

    public abstract List<Schuld> schulden();
}
