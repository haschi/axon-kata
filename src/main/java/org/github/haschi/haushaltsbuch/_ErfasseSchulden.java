package org.github.haschi.haushaltsbuch;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.github.haschi.kata.blueprint.modellierung.de.Aggregatkennung;
import org.github.haschi.kata.blueprint.modellierung.de.Anweisung;

@Anweisung
public interface _ErfasseSchulden {

    @TargetAggregateIdentifier
    Aggregatkennung inventurkennung();

    String position();

    Währungsbetrag währungsbetrag();
}
