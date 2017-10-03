package org.github.haschi.haushaltsbuch;

import cucumber.api.PendingException;
import cucumber.api.java.de.Wenn;
import org.github.haschi.haushaltsbuch.api.BeginneHaushaltsbuchführung;
import org.github.haschi.haushaltsbuch.infrastruktur.modellierung.de.Aggregatkennung;
import org.github.haschi.infrastruktur.Anweisungskonfiguration;

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
}
