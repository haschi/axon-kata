package org.github.haschi.kata.blueprint.taschenrechner;

import cucumber.api.java.de.Dann;
import org.github.haschi.kata.blueprint.infrastruktur.Abfragekonfiguration;
import org.github.haschi.kata.blueprint.taschenrechner.api.ImmutableDisplayAblesen;

import static org.assertj.core.api.Assertions.assertThat;

public class BerechnungSteps {

    private DieWelt welt;

    private Abfragekonfiguration abfragekonfiguration;

    public BerechnungSteps(
            final DieWelt welt,
            final Abfragekonfiguration abfragekonfiguration) {
        this.welt = welt;
        this.abfragekonfiguration = abfragekonfiguration;
    }

    @Dann("^werde ich im Display folgende Ausgabe sehen:$")
    public void werdeIchImDisplayFolgendeAusgabeSehen(final String ausgabe) {

        assertThat(
                (String) abfragekonfiguration.commandGateway()
                    .sendAndWait(ImmutableDisplayAblesen.of(welt.taschenrechnerId)))
                .isEqualTo(ausgabe);
    }
}
