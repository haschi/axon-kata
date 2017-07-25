package org.github.haschi.kata.blueprint.kontext.taschenrechner.api;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.immutables.value.Value;

import java.util.UUID;

public interface DisplayAblesen {
    @TargetAggregateIdentifier
    @Value.Parameter
    UUID taschenrechnerId();
}
