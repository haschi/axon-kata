package org.github.haschi.kata.blueprint.taschenrechner.abfrage;

import org.apache.commons.lang3.NotImplementedException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.github.haschi.kata.blueprint.taschenrechner.api.ImmutableDisplayAblesen;
import org.github.haschi.kata.blueprint.taschenrechner.api.ImmutableTaschenrechnerErzeugt;

public class Display {

    public Display() {
    }

    @CommandHandler
    public String ablesen(final ImmutableDisplayAblesen abfrage) {
        throw new NotImplementedException("Nicht implementiert");
    }

    @EventHandler
    public void falls(final ImmutableTaschenrechnerErzeugt ereignis) {
        throw new NotImplementedException("Nicht implementiert");
    }
}
