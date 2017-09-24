package org.github.haschi.haushaltsbuch;

import cucumber.deps.com.thoughtworks.xstream.annotations.XStreamConverter;
import org.github.haschi.kata.blueprint.modellierung.de.Eingehüllt;
import org.github.haschi.kata.blueprint.modellierung.de.Umhüller;
import org.immutables.value.Value;

import javax.money.MonetaryAmount;

@Value.Immutable
@XStreamConverter(MoneyConverter.class)
@Eingehüllt
public abstract class _Währungsbetrag extends Umhüller<MonetaryAmount> {
}
