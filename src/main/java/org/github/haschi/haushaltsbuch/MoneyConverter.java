package org.github.haschi.haushaltsbuch;

import cucumber.api.Transformer;
import org.apache.commons.lang3.StringUtils;

import javax.money.MonetaryAmount;

public class MoneyConverter
        extends Transformer<Währungsbetrag>
{
    @Override
    public final Währungsbetrag transform(final String währungsbetrag)
    {
        if (StringUtils.isEmpty(währungsbetrag))
        {
            throw new IllegalArgumentException("Währungsbetrag ist leer");
        }

        final DeutschenWährungsbetragAnalysieren analysieren = new DeutschenWährungsbetragAnalysieren();
        final MonetaryAmount betrag = analysieren.aus(währungsbetrag);
        if (betrag.isNegative())
        { // NOPMD LoD TODO
            throw new IllegalArgumentException();
        }

        return Währungsbetrag.of(betrag);
    }
}
