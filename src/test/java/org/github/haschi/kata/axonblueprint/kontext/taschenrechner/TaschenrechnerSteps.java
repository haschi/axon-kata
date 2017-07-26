package org.github.haschi.kata.axonblueprint.kontext.taschenrechner;

import cucumber.api.Transform;
import cucumber.api.java.de.Angenommen;
import cucumber.api.java.de.Dann;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.github.haschi.kata.blueprint.infrastruktur.Abfragekonfiguration;
import org.github.haschi.kata.blueprint.infrastruktur.Anweisungskonfiguration;
import org.github.haschi.kata.blueprint.kontext.taschenrechner.api.ImmutableErzeugeTaschenrechner;

import java.util.UUID;

public class TaschenrechnerSteps {

    private final Anweisungskonfiguration anweisungskonfiguration;
    private final Abfragekonfiguration abfragekonfiguration;
    private DieWelt welt;

    private CommandGateway commandGateway;

    public TaschenrechnerSteps(
            final DieWelt welt,
            final Anweisungskonfiguration anweisungskonfiguration,
            final Abfragekonfiguration abfragekonfiguration) {

        this.welt = welt;
        this.anweisungskonfiguration = anweisungskonfiguration;
        this.abfragekonfiguration = abfragekonfiguration;
    }

    @Angenommen("^ich habe einen Taschenrechner$")
    public void ichHabeEinenTaschenrechner() {
        this.welt.taschenrechnerId = UUID.randomUUID();

        anweisungskonfiguration.commandGateway().sendAndWait(
                ImmutableErzeugeTaschenrechner.of(this.welt.taschenrechnerId));
    }

    @Dann("^werde ich als Ergebnis (\\d+) für die Operation (addiere|subtrahiere|dividiere|multipliziere) berechnet haben$")
    public void werdeIchAlsErgebnisErhaltenHaben(
            final int zahl,
            @Transform(OperationConverter.class) final char operator) {
    }

    @Angenommen("^ich habe ein Ergebnis von (\\d+) für die Operation (addiere|subtrahiere|dividiere|multipliziere) berechnet$")
    public void ichHabeEinErgebnisVonBerechnet(
            final int ergebnis,
            @Transform(OperationConverter.class) final char operation) {
    }

    @Dann("^werde ich einen Fehler gemacht haben$")
    public void werdeIchEinenFehlerErhaltenHaben() {
    }
}
