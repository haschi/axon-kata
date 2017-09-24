package org.github.haschi.haushaltsbuch;

import org.github.haschi.kata.blueprint.modellierung.de.Ereignis;

@Ereignis
public interface _SchuldErfasst {
    String position();

    Währungsbetrag währungsbetrag();
}
