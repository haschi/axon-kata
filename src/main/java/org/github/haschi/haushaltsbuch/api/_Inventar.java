package org.github.haschi.haushaltsbuch.api;

import org.github.haschi.haushaltsbuch.infrastruktur.modellierung.de.Information;
import org.immutables.value.Value;
import org.javamoney.moneta.function.MonetaryFunctions;

import javax.money.MonetaryAmount;
import java.util.List;
import java.util.stream.Collectors;

@Information
public abstract class _Inventar {
    public static Inventar leer() {
        return Inventar.builder().build();
    }

    public abstract List<Vermoegenswert> vermoegenswerte();
    public abstract List<Vermoegenswert> anlagevermögen();
    public abstract List<Vermoegenswert> umlaufvermögen();

    public abstract List<Schuld> schulden();

    @Value.Derived
    public Reinvermögen reinvermögen() {
        MonetaryAmount anlagevermögen = anlagevermögen().stream()
                .map(m -> m.währungsbetrag().wert())
                .reduce(MonetaryFunctions.sum())
                .orElse(Währungsbetrag.NullEuro().wert());

        MonetaryAmount umlaufvermögen = umlaufvermögen().stream()
                .map(m -> m.währungsbetrag().wert())
                .reduce(MonetaryFunctions.sum())
                .orElse(Währungsbetrag.NullEuro().wert());

        MonetaryAmount schulden = schulden().stream()
                .map(m -> m.währungsbetrag().wert())
                .reduce(MonetaryFunctions.sum())
                .orElse(Währungsbetrag.NullEuro().wert());

        return Reinvermögen.builder()
                .summeDesVermögens(Währungsbetrag.of(anlagevermögen.add(umlaufvermögen)))
                .summeDerSchulden(Währungsbetrag.of(schulden))
                .build();
    }
}
