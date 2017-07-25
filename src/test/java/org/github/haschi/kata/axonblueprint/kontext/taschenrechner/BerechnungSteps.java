package org.github.haschi.kata.axonblueprint.kontext.taschenrechner;

import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Wenn;
import org.github.haschi.kata.blueprint.infrastruktur.Abfragekonfiguration;

public class BerechnungSteps {

    private DieWelt welt;

    private Abfragekonfiguration abfragekonfiguration;

    public BerechnungSteps(final Abfragekonfiguration abfragekonfiguration) {

        this.abfragekonfiguration = abfragekonfiguration;
    }
    @Wenn("^ich die Berechnung ansehe$")
    public void ichDieBerechnungAnsehe() {
    }

    @Dann("^werde ich folgende Ausgabe erhalten:$")
    public void werdeIchFolgendeAusgabeErhalten(final String ausgabe) {}
}
