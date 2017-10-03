package org.github.haschi.haushaltsbuch.api;

import cucumber.api.Transformer;
import org.github.haschi.haushaltsbuch.infrastruktur.MoneyConverter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuchungConverter extends Transformer<Buchung> {
    @Override
    public Buchung transform(String s) {
        Pattern pattern = Pattern.compile("^(.*) (-?(?:\\d{1,3}\\.)?\\d{1,3},\\d{2} EUR)$");
        Matcher matcher = pattern.matcher(s);

        assert matcher.matches() : "Keine Übereinstimmung: " + s;

        if (matcher.matches()) {
            return Buchung.builder()
                    .buchungstext(matcher.group(1))
                    .betrag(Währungsbetrag.parse(matcher.group(2)))
                    .build();
        } else {
            return _Buchung.leer();
        }
    }
}
