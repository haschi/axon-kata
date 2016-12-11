package org.github.haschi.kata.axonblueprint.kontext.taschenrechner;

import cucumber.api.Transform;
import cucumber.api.java.de.Angenommen;
import cucumber.api.java.de.Dann;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.github.haschi.kata.blueprint.kontext.taschenrechner.aggregat.Taschenrechner;
import org.github.haschi.kata.blueprint.kontext.taschenrechner.api.*;

import javax.inject.Inject;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class TaschenrechnerSteps {
    @Inject
    private DieWelt welt;

    @Inject
    private CommandGateway commandGateway;

    @Angenommen("^ich habe einen Taschenrechner$")
    public void ichHabeEinenTaschenrechner() {
        this.welt.taschenrechnerId = UUID.randomUUID();
        this.commandGateway.sendAndWait(ImmutableErzeugeTaschenrechner.of(this.welt.taschenrechnerId));
    }

    @Dann("^werde ich als Ergebnis (\\d+) für die Operation (addiere|subtrahiere|dividiere|multipliziere) erhalten haben$")
    public void werdeIchAlsErgebnisErhaltenHaben(
            final int zahl,
            @Transform(OperationConverter.class) final char operator) {
        assertThat(this.welt.letztesEreignis()).isEqualTo(
                ImmutableErgebnisBerechnet.of(this.welt.taschenrechnerId, zahl, operator));
    }

    @Angenommen("^ich habe ein Ergebnis von (\\d+) für die Operation (addiere|subtrahiere|dividiere|multipliziere) berechnet$")
    public void ichHabeEinErgebnisVonBerechnet(
            final int ergebnis,
            @Transform(OperationConverter.class) final char operation) {
        this.welt.taschenrechnerId = UUID.randomUUID();
        this.welt.speichern(this.welt.taschenrechnerId, Taschenrechner.class,
                ImmutableTaschenrechnerErzeugt.of(this.welt.taschenrechnerId),
                ImmutableZahlEingegeben.of(this.welt.taschenrechnerId, ergebnis - 4),
                ImmutableZahlEingegeben.of(this.welt.taschenrechnerId, 4),
                ImmutableErgebnisBerechnet.of(this.welt.taschenrechnerId, ergebnis, operation));
    }

    @Dann("^werde ich einen Fehler erhalten haben$")
    public void werdeIchEinenFehlerErhaltenHaben() {
        assertThat(this.welt.letztesEreignis())
                .isEqualTo(ImmutableFehlerAufgetreten.of(this.welt.taschenrechnerId));
    }
}
