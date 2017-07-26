package org.github.haschi.kata.axonblueprint.kontext.taschenrechner;

import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Wenn;
import org.github.haschi.kata.blueprint.infrastruktur.Anweisungskonfiguration;
import org.github.haschi.kata.blueprint.kontext.taschenrechner.api.ImmutableGebeZahlEin;

public class ZahlEingebenSteps {

    private DieWelt welt;
    private Anweisungskonfiguration configuration;

    public ZahlEingebenSteps(final DieWelt welt, final Anweisungskonfiguration configuration) {
        this.welt = welt;
        this.configuration = configuration;
    }

        @Wenn("^ich die Zahl (\\d+) eingebe$")
    public void ichHabeDieZahlEingebe(final int zahl) {
        configuration.commandGateway().sendAndWait(
                ImmutableGebeZahlEin.of(welt.taschenrechnerId, zahl));
    }

    @Dann("^werde ich die Zahl (\\d+) eingeben haben$")
    public void werdeIchDieZahlEingebenHaben(final int zahl) {
    }
}
