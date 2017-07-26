package org.github.haschi.kata.blueprint.taschenrechner;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.de.Angenommen;
import cucumber.api.java.de.Dann;
import org.github.haschi.kata.blueprint.infrastruktur.Anweisungskonfiguration;
import org.github.haschi.kata.blueprint.taschenrechner.api.ImmutableErzeugeTaschenrechner;

import java.util.UUID;

public class TaschenrechnerSteps {

    private final Anweisungskonfiguration anweisungskonfiguration;
    private DieWelt welt;

    public TaschenrechnerSteps(
            final DieWelt welt,
            final Anweisungskonfiguration anweisungskonfiguration) {

        this.welt = welt;
        this.anweisungskonfiguration = anweisungskonfiguration;
    }

    @Angenommen("^ich habe einen Taschenrechner$")
    public void ichHabeEinenTaschenrechner() {
        this.welt.taschenrechnerId = UUID.randomUUID();

        anweisungskonfiguration.commandGateway().sendAndWait(
                ImmutableErzeugeTaschenrechner.of(this.welt.taschenrechnerId));
    }

    @Dann("^werde ich als Ergebnis (\\d+) für die Operation (addiere|subtrahiere|dividiere|multipliziere) berechnet haben$")
    public void werdeIchAlsErgebnisfürDieOperationBerechnetHaben(
            final int zahl,
            @Transform(OperationConverter.class) final char operator) {
        throw new PendingException();
    }

    @Angenommen("^ich habe ein Ergebnis von (\\d+) für eine beliebige Operation berechnet$")
    public void ichHabeEinErgebnisVonFürEineBeliebigeOperationBerechnet(final int ergebnis) {
        throw new PendingException();
    }

    @Dann("^werde ich einen Fehler gemacht haben$")
    public void werdeIchEinenFehlerGemachtHaben() {
        throw new PendingException();
    }
}
