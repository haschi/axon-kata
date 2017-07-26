package org.github.haschi.kata.blueprint.taschenrechner;

import cucumber.api.java.de.Angenommen;
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

    @Angenommen("^ich habe die Zahl (\\d+) eingegeben$")
    public void ichHabeDieZahlEingegeben(final int zahl) {
        configuration.commandGateway()
                .sendAndWait(ImmutableGebeZahlEin.of(this.welt.taschenrechnerId, zahl));
    }

    @Wenn("^ich die Zahl (-{0,1}\\d+) eingebe$")
    public void ichDieZahlEingebe(final int zahl) {
        configuration.commandGateway().sendAndWait(
                ImmutableGebeZahlEin.of(welt.taschenrechnerId, zahl));
    }

    @Dann("^werde ich die Zahl (-{0,1}\\d+) eingeben haben$")
    public void werdeIchDieZahlEingebenHaben(final int zahl) {
    }

    @Wenn("^ich eine beliebige Zahl eingebe$")
    public void ichEineBeliebigeZahlEingebe() {
        ichDieZahlEingebe(42);
    }

    @Wenn("^ich keine Zahl eingebe$")
    public void ichKeineZahlEingebe() {
    }

}
