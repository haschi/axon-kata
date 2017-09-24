package org.github.haschi.haushaltsbuch;

import org.axonframework.commandhandling.CommandHandler;

public class InventarProjektion {

    @CommandHandler
    public Inventar zeigeInventar(final ZeigeInventar abfrage) {
        return Inventar.leer();
    }
}
