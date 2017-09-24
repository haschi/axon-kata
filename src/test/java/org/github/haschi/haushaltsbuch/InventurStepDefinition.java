package org.github.haschi.haushaltsbuch;

import cucumber.api.Transform;
import cucumber.api.java.de.Angenommen;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Wenn;
import org.github.haschi.infrastruktur.Abfragekonfiguration;
import org.github.haschi.infrastruktur.Anweisungskonfiguration;
import org.github.haschi.kata.blueprint.modellierung.de.Aggregatkennung;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InventurStepDefinition {


    private DieWelt welt;
    private final Anweisungskonfiguration anweisung;
    private final Abfragekonfiguration abfrage;

    public InventurStepDefinition(
            final DieWelt welt,
            final Anweisungskonfiguration anweisung,
            final Abfragekonfiguration abfrage) {
        this.welt = welt;
        this.anweisung = anweisung;
        this.abfrage = abfrage;
    }

    @Wenn("^ich die Inventur beginne$")
    public void wenn_ich_die_inventur_beginne() {

        welt.aktuelleInventur = Aggregatkennung.neu();
        anweisung.commandGateway().sendAndWait(
                BeginneInventur.of(welt.aktuelleInventur));
    }

    @Dann("^wird mein Inventar leer sein$")
    public void wirdMeinInventarLeerSein() throws Throwable {

        final Inventar inventar = abfrage.commandGateway().sendAndWait(
                ZeigeInventar.of(welt.aktuelleInventur));

        assertThat(inventar)
            .isEqualTo(_Inventar.leer());
    }

    @Angenommen("^ich habe mit der Inventur begonnen$")
    public void ichHabeMitDerInventurBegonnen()  {
        wenn_ich_die_inventur_beginne();
    }

    @Wenn("^ich mein Umlaufvermögen \"([^\"]*)\" in Höhe von \"([^\"]*)\" erfasse$")
    public void ichMeinUmlaufvermögenInHöheVonErfasse(
            final String position,
            final @Transform(MoneyConverter.class) Währungsbetrag währungsbetrag) {

        anweisung.commandGateway().sendAndWait(
                ErfasseUmlaufvermögen.builder()
                        .inventurkennung(welt.aktuelleInventur)
                        .position(position)
                        .währungsbetrag(währungsbetrag)
                        .build());
    }

    @Dann("^werde ich folgende Vermögenswerte in meinem Inventar gelistet haben:$")
    public void werdeIchFolgendeVermögenswerteInMeinemInventarGelistetHaben(final List<Vermoegenswert> vermögenswerte) {
        final Inventar inventar = abfrage.commandGateway().sendAndWait(
                ZeigeInventar.of(welt.aktuelleInventur));

        assertThat(inventar.vermoegenswerte())
                .containsExactly(vermögenswerte.toArray(new Vermoegenswert[vermögenswerte.size()]));
    }

    @Wenn("^ich  meine Schulden \"([^\"]*)\" in Höhe von \"([^\"]*)\" erfasse$")
    public void ichMeineSchuldenInHöheVonErfasse(
            final String position,
            final @Transform(MoneyConverter.class) Währungsbetrag währungsbetrag) {

        anweisung.commandGateway().sendAndWait(
                ErfasseSchulden.builder()
                        .inventurkennung(welt.aktuelleInventur)
                        .position(position)
                        .währungsbetrag(währungsbetrag)
                        .build());
    }

    @Dann("^werde ich folgende Schulden in meinem Inventar gelistet haben:$")
    public void werdeIchFolgendeSchuldenInMeinemInventarGelistetHaben(final List<Schuld> schulden) {
        final Inventar inventar = abfrage.commandGateway().sendAndWait(
                ZeigeInventar.of(welt.aktuelleInventur));

        assertThat(inventar.schulden())
                .containsExactly(schulden.toArray(new Schuld[schulden.size()]));
    }
}
