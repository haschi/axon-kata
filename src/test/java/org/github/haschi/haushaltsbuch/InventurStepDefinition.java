package org.github.haschi.haushaltsbuch;

import cucumber.api.java.de.Wenn;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.github.haschi.infrastruktur.Anweisungskonfiguration;
import org.github.haschi.kata.blueprint.modellierung.de.Aggregatkennung;

public class InventurStepDefinition {


    private Anweisungskonfiguration anweisung;

    public InventurStepDefinition(Anweisungskonfiguration anweisung) {
        this.anweisung = anweisung;
    }

    @Wenn("^ich die Inventur beginne$")
    public void ichDieInventurBeginne() throws Throwable {
        final BeginneInventur beginneInventur = BeginneInventur.of(Aggregatkennung.neu());

        final CommandGateway commandGateway = anweisung.commandGateway();
        commandGateway.sendAndWait(beginneInventur);
    }
}
