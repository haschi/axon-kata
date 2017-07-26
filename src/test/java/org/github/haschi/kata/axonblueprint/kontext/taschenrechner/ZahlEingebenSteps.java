package org.github.haschi.kata.axonblueprint.kontext.taschenrechner;

import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Wenn;
import org.github.haschi.kata.blueprint.infrastruktur.Anweisungskonfiguration;

public class ZahlEingebenSteps {

    private Anweisungskonfiguration configuration;

    public ZahlEingebenSteps(final Anweisungskonfiguration configuration) {
        this.configuration = configuration;
    }

        @Wenn("^ich die Zahl (\\d+) eingebe$")
    public void ichHabeDieZahlEingebe(final int zahl) {
    }

    @Dann("^werde ich die Zahl (\\d+) eingeben haben$")
    public void werdeIchDieZahlEingebenHaben(final int zahl) {
    }
}
