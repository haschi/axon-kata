package org.github.haschi.haushaltsbuch;

import org.github.haschi.kata.blueprint.modellierung.de.Information;

@Information
public interface _Schuld {
    String position();

    Währungsbetrag währungsbetrag();
}
