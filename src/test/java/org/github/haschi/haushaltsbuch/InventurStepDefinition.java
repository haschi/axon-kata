package org.github.haschi.haushaltsbuch;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.de.Angenommen;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Und;
import cucumber.api.java.de.Wenn;
import org.axonframework.commandhandling.CommandExecutionException;
import org.github.haschi.haushaltsbuch.api.*;
import org.github.haschi.haushaltsbuch.infrastruktur.MoneyConverter;
import org.github.haschi.infrastruktur.Abfragekonfiguration;
import org.github.haschi.infrastruktur.Anweisungskonfiguration;
import org.github.haschi.haushaltsbuch.infrastruktur.modellierung.de.Aggregatkennung;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.catchThrowable;

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

    @Dann("^werde ich folgendes Umlaufvermögen in meinem Inventar gelistet haben:$")
    public void werdeIchFolgendeVermögenswerteInMeinemInventarGelistetHaben(final List<Vermoegenswert> vermögenswerte) {
        final Inventar inventar = abfrage.commandGateway().sendAndWait(
                LeseInventar.of(welt.aktuelleInventur));

        assertThat(inventar.umlaufvermögen())
                .containsExactly(vermögenswerte.toArray(new Vermoegenswert[vermögenswerte.size()]));
    }

    @Dann("^werde ich folgendes Anlagevermögen in meinem Inventar gelistet haben:$")
    public void werdeIchFolgendesAnlagevermögenInMeinemInventarGelistetHaben(final List<Vermoegenswert> vermögenswerte) {
        final Inventar inventar = abfrage.commandGateway().sendAndWait(
                LeseInventar.of(welt.aktuelleInventur));

        assertThat(inventar.anlagevermögen())
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
                LeseInventar.of(welt.aktuelleInventur));

        assertThat(inventar.schulden())
                .containsExactly(schulden.toArray(new Schuld[schulden.size()]));
    }

    @Wenn("^ich folgendes Inventar erfasse:$")
    public void ichFolgendesInventarErfasse(final List<Inventarposition> zeilen) {

        Inventar inventar = Inventar.builder()
                .addAllUmlaufvermögen(zeilen.stream()
                        .filter(z -> z.untergruppe.equals("Umlaufvermögen"))
                        .map(z -> Vermoegenswert.builder()
                                .position(z.position)
                                .währungsbetrag(z.währungsbetrag)
                                .build())
                        .collect(Collectors.toList()))
                .addAllAnlagevermögen(zeilen.stream()
                        .filter(z -> z.untergruppe.equals("Anlagevermögen"))
                        .map(z -> Vermoegenswert.builder()
                                .position(z.position)
                                .währungsbetrag(z.währungsbetrag)
                                .build())
                        .collect(Collectors.toList()))
                .addAllSchulden(zeilen.stream()
                    .filter(z -> z.untergruppe.equals("Langfristige Schulden"))
                    .map(z -> Schuld.builder()
                            .position(z.position)
                            .währungsbetrag(z.währungsbetrag)
                            .build())
                    .collect(Collectors.toList()))
                .build();

        anweisung.commandGateway().sendAndWait(
        ErfasseInventar.builder()
                .für(welt.aktuelleInventur)
                .inventar(inventar)
                .build());
    }

    @Und("^ich folgendes Inventar erfassen will:$")
    public void ichFolgendesInventarErfassenWill(final List<Inventarposition> zeilen) {
        welt.intention = () -> ichFolgendesInventarErfasse(zeilen);
    }

    @Dann("^werde ich folgendes Reinvermögen ermittelt haben:$")
    public void werdeIchFolgendesEigenkapitalErmitteltHaben(DataTable reinvermögen) throws Throwable {

        Map<String, Währungsbetrag> map = reinvermögen.asMap(String.class, Währungsbetrag.class);

        Reinvermögen erwartungswert = Reinvermögen.builder()
                .summeDerSchulden(map.get("Summe der Schulden"))
                .summeDesVermögens(map.get("Summe des Vermögens"))
                .build();

        final Inventar inventar = abfrage.commandGateway().sendAndWait(
                LeseInventar.of(welt.aktuelleInventur));

        assertThat(inventar.reinvermögen()).isEqualTo(erwartungswert);
    }

    @Wenn("^ich die Inventur beenden will$")
    public void ichDieInventurBeendenWill() {
        welt.intention = () -> anweisung.commandGateway().sendAndWait(
            BeendeInventur.builder()
                    .von(welt.aktuelleInventur)
                    .build());
    }

    @Dann("^werde ich die Fehlermeldung \"([^\"]*)\" erhalten$")
    public void werdeIchDieFehlermeldungErhalten(String fehlermeldung) {
        assert welt.intention != null : "Es wurde kein Schritt ausgeführt, der eine Intention ausdrückt.";

        assertThat(catchThrowable(welt.intention))
                .hasCause(new InventurAusnahme(fehlermeldung));
    }

    @Und("^ich habe folgendes Inventar erfasst:$")
    public void ichHabeFolgendesInventarErfasst(final List<Inventarposition> zeilen) {
        ichFolgendesInventarErfasse(zeilen);
    }

    @Wenn("^ich die Inventur beende$")
    public void ichDieInventurBeende() {
        anweisung.commandGateway().sendAndWait(
                BeendeInventur.of(welt.aktuelleInventur));
    }

}
