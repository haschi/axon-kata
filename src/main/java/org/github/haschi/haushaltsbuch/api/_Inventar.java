package org.github.haschi.haushaltsbuch.api;

import org.github.haschi.haushaltsbuch.infrastruktur.modellierung.de.Information;

import java.util.List;

@Information
public abstract class _Inventar {
    public static Inventar leer() {
        return Inventar.builder().build();
    }

    public abstract List<Vermoegenswert> vermoegenswerte();

    public abstract List<Schuld> schulden();
}
