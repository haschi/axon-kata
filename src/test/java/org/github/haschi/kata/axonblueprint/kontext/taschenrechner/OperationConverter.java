package org.github.haschi.kata.axonblueprint.kontext.taschenrechner;

import cucumber.api.Transformer;

import static javaslang.API.Case;
import static javaslang.API.Match;
import static javaslang.Predicates.is;

public class OperationConverter extends Transformer<Character> {
    @Override
    public Character transform(final String s) {
        return Match(s).of(
                Case(is("addiere"), '+'),
                Case(is("subtrahiere"), '-'),
                Case(is("multipliziere"), '*'),
                Case(is("dividiere"), '/'));
    }
}
