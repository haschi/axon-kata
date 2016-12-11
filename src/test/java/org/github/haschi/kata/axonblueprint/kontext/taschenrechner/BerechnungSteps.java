package org.github.haschi.kata.axonblueprint.kontext.taschenrechner;

import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Wenn;
import org.github.haschi.kata.blueprint.kontext.taschenrechner.Abfrage.Berechnung;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

public class BerechnungSteps {
    @Inject
    private DieWelt welt;

    @Inject
    private Berechnung berechnung;

    @Wenn("^ich die Berechnung ansehe$")
    public void ichDieBerechnungAnsehe() {
        this.welt.berechnung = this.berechnung.abfragen(this.welt.taschenrechnerId);
    }

    @Dann("^werde ich folgende Ausgabe erhalten:$")
    public void werdeIchFolgendeAusgabeErhalten(final String ausgabe) {
        assertThat(this.welt.berechnung)
                .isEqualTo(ausgabe);
    }
}
