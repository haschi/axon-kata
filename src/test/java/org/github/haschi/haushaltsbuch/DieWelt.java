package org.github.haschi.haushaltsbuch;

import org.assertj.core.api.ThrowableAssert;
import org.github.haschi.haushaltsbuch.infrastruktur.modellierung.de.Aggregatkennung;

public class DieWelt {
    public Aggregatkennung aktuelleInventur;
    public ThrowableAssert.ThrowingCallable intention;
}
