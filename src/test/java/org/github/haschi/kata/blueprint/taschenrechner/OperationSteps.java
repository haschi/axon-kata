package org.github.haschi.kata.blueprint.taschenrechner;

import cucumber.api.java.de.Angenommen;
import cucumber.api.java.de.Wenn;
import org.github.haschi.kata.blueprint.infrastruktur.Anweisungskonfiguration;
import org.github.haschi.kata.blueprint.kontext.taschenrechner.api.*;

import java.util.List;

public class OperationSteps {

    private final DieWelt welt;
    private final Anweisungskonfiguration anweisungskonfiguration;

    public OperationSteps(final DieWelt welt, final Anweisungskonfiguration anweisungskonfiguration) {

        this.welt = welt;
        this.anweisungskonfiguration = anweisungskonfiguration;
    }

    @Angenommen("^ich habe die folgenden Zahlen eingeben: (.*)$")
    public void ichHabeDieFolgendenZahlenEingeben(final List<Integer> zahlen) {
        for (final Integer zahl : zahlen) {
            this.anweisungskonfiguration.commandGateway()
                    .sendAndWait(ImmutableGebeZahlEin.of(this.welt.taschenrechnerId, zahl));
        }
    }

    @Wenn("^ich folgende Zahlen eingebe: (.*)$")
    public void ichFolgendeZahlenEingebe(final List<Integer> zahlen) {
        for (final Integer zahl : zahlen) {
            this.anweisungskonfiguration.commandGateway()
                    .sendAndWait(ImmutableGebeZahlEin.of(this.welt.taschenrechnerId, zahl));
        }
    }

    @Wenn("^ich addiere$")
    public void ichAddiere() {
        anweisungskonfiguration.commandGateway()
                .sendAndWait(ImmutableAddiere.of(this.welt.taschenrechnerId));
    }

    @Wenn("^ich subtrahiere")
    public void ichSubtrahiere() {
        anweisungskonfiguration.commandGateway()
                .sendAndWait(ImmutableSubtrahiere.of(this.welt.taschenrechnerId));
    }

    @Wenn("^ich multipliziere")
    public void ichMultipliziere() {
        anweisungskonfiguration.commandGateway()
                .sendAndWait(ImmutableMultipliziere.of(this.welt.taschenrechnerId));
    }

    @Wenn("^ich dividiere")
    public void ichDividiere() {
        anweisungskonfiguration.commandGateway()
                .sendAndWait(ImmutableDividiere.of(this.welt.taschenrechnerId));
    }
}
