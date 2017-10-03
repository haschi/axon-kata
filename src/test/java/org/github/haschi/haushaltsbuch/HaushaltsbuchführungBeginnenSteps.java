package org.github.haschi.haushaltsbuch;

import cucumber.api.DataTable;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Wenn;
import org.axonframework.eventsourcing.DomainEventMessage;
import org.github.haschi.haushaltsbuch.api.BeginneHaushaltsbuchführung;
import org.github.haschi.haushaltsbuch.api.Eröffnungsbilanzkonto;
import org.github.haschi.haushaltsbuch.api.EröffnungsbilanzkontoErstellt;
import org.github.haschi.haushaltsbuch.infrastruktur.modellierung.de.Aggregatkennung;
import org.github.haschi.infrastruktur.Anweisungskonfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HaushaltsbuchführungBeginnenSteps {
    private DieWelt welt;
    private Anweisungskonfiguration anweisung;

    public HaushaltsbuchführungBeginnenSteps(final DieWelt welt, final Anweisungskonfiguration anweisung) {

        this.welt = welt;
        this.anweisung = anweisung;
    }

    @Wenn("^ich die Haushaltsbuchführung beginne$")
    public void ichDieHaushaltsbuchführungBeginne() {

        welt.aktuellesHaushaltsbuch = Aggregatkennung.neu();

        anweisung.konfiguration().commandGateway().sendAndWait(
                BeginneHaushaltsbuchführung.builder()
                        .id(welt.aktuellesHaushaltsbuch)
                        .build());
    }

    @Dann("^werde ich folgendes Eröffnungsbilanzkonto im Hauptbuch erstellt haben:$")
    public void werdeIchFolgendesEröffnungsbilanzkontoImHauptbuchErstelltHaben(final List<Kontozeile> eröffnungsbilanzkonto) throws Exception {

        final EröffnungsbilanzkontoErstellt eröffnungsbilanzkontoErstellt = anweisung.konfiguration().eventStore()
                .readEvents(welt.aktuellesHaushaltsbuch.toString())
                .asStream()
                .filter(m -> m.getPayloadType().isAssignableFrom(EröffnungsbilanzkontoErstellt.class))
                .map(m -> (EröffnungsbilanzkontoErstellt) m.getPayload())
                .findFirst()
                .orElseThrow(() -> new Exception("Kein Eröffnungsbilanzkonto erstellt"));

        assertThat(eröffnungsbilanzkontoErstellt.eröffnungsbilanzkonto())
                .isEqualTo(Eröffnungsbilanzkonto.builder().build());
    }
}
