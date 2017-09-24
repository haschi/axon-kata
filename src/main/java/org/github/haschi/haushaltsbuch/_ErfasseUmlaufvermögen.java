package org.github.haschi.haushaltsbuch;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.github.haschi.kata.blueprint.modellierung.de.Aggregatkennung;
import org.github.haschi.kata.blueprint.modellierung.de.Anweisung;

@Anweisung
public interface _ErfasseUmlaufvermögen {
    @TargetAggregateIdentifier
    Aggregatkennung inventurkennung();
}
