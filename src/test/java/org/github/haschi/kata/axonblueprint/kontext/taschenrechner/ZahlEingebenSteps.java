package org.github.haschi.kata.axonblueprint.kontext.taschenrechner;

import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Wenn;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.github.haschi.kata.blueprint.kontext.taschenrechner.api.ImmutableGebeZahlEin;
import org.github.haschi.kata.blueprint.kontext.taschenrechner.api.ImmutableZahlEingegeben;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

public class ZahlEingebenSteps {
    @Inject
    private CommandGateway commandGateway;

    @Inject
    private DieWelt welt;

    @Wenn("^ich die Zahl (\\d+) eingebe$")
    public void ichHabeDieZahlEingebe(final int zahl) {
        this.commandGateway.sendAndWait(ImmutableGebeZahlEin.of(this.welt.taschenrechnerId, zahl));
    }

    @Dann("^werde ich die Zahl (\\d+) eingeben haben$")
    public void werdeIchDieZahlEingebenHaben(final int zahl) {
        assertThat(this.welt.letztesEreignis())
                .isEqualTo(ImmutableZahlEingegeben.of(this.welt.taschenrechnerId, zahl));
    }
}
