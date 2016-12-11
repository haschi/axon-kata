package org.github.haschi.kata.axonblueprint.kontext.taschenrechner;

import cucumber.api.java.de.Angenommen;
import cucumber.api.java.de.Wenn;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.github.haschi.kata.blueprint.kontext.taschenrechner.api.*;

import javax.inject.Inject;
import java.util.List;

public class AdditionSteps {

    @Inject
    private DieWelt welt;

    @Inject
    private CommandGateway commandGateway;

    @Angenommen("^ich habe die folgenden Zahlen eingeben: (.*)$")
    public void ichHabeDieZahlEingeben(
            final List<Integer> zahlen) throws Throwable {
        for (final Integer zahl : zahlen) {
            System.out.printf("Parameter: %d%n", zahl);
            this.commandGateway.sendAndWait(ImmutableGebeZahlEin.of(this.welt.taschenrechnerId, zahl));
        }
    }

    @Wenn("^ich addiere$")
    public void ichAddiere() throws Throwable {
        this.commandGateway.sendAndWait(ImmutableAddiere.of(this.welt.taschenrechnerId));
    }

    @Wenn("^ich subtrahiere")
    public void ichSubtrahiere() throws Throwable {
        this.commandGateway.sendAndWait(ImmutableSubtrahiere.of(this.welt.taschenrechnerId));
    }

    @Wenn("^ich multipliziere")
    public void ichMultipliziere() throws Throwable {
        this.commandGateway.sendAndWait(ImmutableMultipliziere.of(this.welt.taschenrechnerId));
    }

    @Wenn("^ich dividiere")
    public void ichDividiere() throws Throwable {
        this.commandGateway.sendAndWait(ImmutableDividiere.of(this.welt.taschenrechnerId));
    }

    @Angenommen("^ich habe die Zahl (\\d+) eingegeben$")
    public void ichHabeDieZahlEingegeben(final int zahl) throws Throwable {
        this.commandGateway.sendAndWait(ImmutableGebeZahlEin.of(this.welt.taschenrechnerId, zahl));
    }
}
