package org.github.haschi.haushaltsbuch.api;

import org.github.haschi.haushaltsbuch.infrastruktur.modellierung.de.Information;
import org.immutables.value.Value;
import org.javamoney.moneta.function.MonetaryFunctions;

import javax.money.MonetaryAmount;
import java.util.List;

@Information
public abstract class _Inventar {
    public static Inventar leer() {
        return Inventar.builder().build();
    }

    public abstract Vermögenswerte anlagevermögen();
    public abstract Vermögenswerte umlaufvermögen();
    public abstract Schulden schulden();

    @Value.Derived
    public Reinvermögen reinvermögen() {
        Währungsbetrag anlagevermögen = anlagevermögen().summe();

        Währungsbetrag umlaufvermögen = umlaufvermögen().summe();

        Währungsbetrag schulden = schulden().summe();

        return Reinvermögen.builder()
                .summeDesVermögens(Währungsbetrag.of(anlagevermögen.wert().add(umlaufvermögen.wert())))
                .summeDerSchulden(schulden)
                .build();
    }
}
