package org.github.haschi.haushaltsbuch;

import org.github.haschi.kata.blueprint.modellierung.de.Information;

@Information
public interface _Vermoegenswert {
    String position();


    Währungsbetrag währungsbetrag();
}
