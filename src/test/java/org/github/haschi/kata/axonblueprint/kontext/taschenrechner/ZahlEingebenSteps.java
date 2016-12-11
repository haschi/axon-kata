package org.github.haschi.kata.axonblueprint.kontext.taschenrechner;

import cucumber.api.java.de.Dann;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.github.haschi.kata.blueprint.kontext.taschenrechner.api.ImmutableZahlEingegeben;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

public class ZahlEingebenSteps {
    @Inject
    private CommandGateway commandGateway;

    @Inject
    private DieWelt welt;

    @Dann("^werde ich die Zahl (\\d+) eingeben haben$")
    public void werdeIchDieZahlEingebenHaben(final int zahl) throws Throwable {
        assertThat(this.welt.letztesEreignis())
                .isEqualTo(ImmutableZahlEingegeben.of(this.welt.taschenrechnerId, zahl));
    }
}
