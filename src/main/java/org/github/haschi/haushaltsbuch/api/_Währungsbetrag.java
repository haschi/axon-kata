package org.github.haschi.haushaltsbuch.api;

import cucumber.deps.com.thoughtworks.xstream.annotations.XStreamConverter;
import org.apache.commons.lang3.StringUtils;
import org.github.haschi.haushaltsbuch.infrastruktur.MoneyConverter;
import org.github.haschi.haushaltsbuch.infrastruktur.modellierung.de.Eingehüllt;
import org.github.haschi.haushaltsbuch.infrastruktur.modellierung.de.Umhüller;
import org.immutables.value.Value;

import javax.money.MonetaryAmount;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import java.util.Locale;

@Value.Immutable
@XStreamConverter(MoneyConverter.class)
@Eingehüllt
public abstract class _Währungsbetrag extends Umhüller<MonetaryAmount> {
    public static Währungsbetrag parse(final String s) {
        if (StringUtils.isEmpty(s))
        {
            throw new IllegalArgumentException("Währungsbetrag ist leer");
        }

        final DeutschenWährungsbetragAnalysieren analysieren = new DeutschenWährungsbetragAnalysieren();
        final MonetaryAmount betrag = analysieren.aus(s);
        if (betrag.isNegative())
        { // NOPMD LoD TODO
            throw new IllegalArgumentException();
        }

        return Währungsbetrag.of(betrag);
    }

    @Override
    public String toString() {
        MonetaryAmountFormat germanFormat = MonetaryFormats.getAmountFormat(Locale.GERMANY);
        return germanFormat.format(wert());
    }
}
