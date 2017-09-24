package org.github.haschi.haushaltsbuch;

import cucumber.api.Transformer;

public class MoneyConverter
        extends Transformer<Währungsbetrag>
{
    @Override
    public final Währungsbetrag transform(final String währungsbetrag)
    {
        return Währungsbetrag.parse(währungsbetrag);
    }
}
