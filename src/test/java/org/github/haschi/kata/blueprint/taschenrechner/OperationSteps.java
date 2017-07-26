package org.github.haschi.kata.blueprint.taschenrechner;

import cucumber.api.PendingException;
import cucumber.api.java.de.Angenommen;
import cucumber.api.java.de.Wenn;
import org.github.haschi.kata.blueprint.infrastruktur.Anweisungskonfiguration;

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
        throw new PendingException();
    }

    @Wenn("^ich folgende Zahlen eingebe: (.*)$")
    public void ichFolgendeZahlenEingebe(final List<Integer> zahlen) {
        throw new PendingException();
    }

    @Wenn("^ich addiere$")
    public void ichAddiere() {
        throw new PendingException();
    }

    @Wenn("^ich subtrahiere")
    public void ichSubtrahiere() {
        throw new PendingException();
    }

    @Wenn("^ich multipliziere")
    public void ichMultipliziere() {
        throw new PendingException();
    }

    @Wenn("^ich dividiere")
    public void ichDividiere() {
        throw new PendingException();
    }
}
