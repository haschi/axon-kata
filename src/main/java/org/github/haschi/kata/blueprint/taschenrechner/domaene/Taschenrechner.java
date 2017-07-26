package org.github.haschi.kata.blueprint.taschenrechner.domaene;

import org.apache.commons.lang3.NotImplementedException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.github.haschi.kata.blueprint.taschenrechner.api.ImmutableErzeugeTaschenrechner;

import java.util.UUID;

public class Taschenrechner {

    @AggregateIdentifier
    private UUID id;

    private Taschenrechner() {
    }

    @CommandHandler
    public Taschenrechner(final ImmutableErzeugeTaschenrechner ereignis) {
        throw new NotImplementedException("Nicht implementiert");
    }
}
