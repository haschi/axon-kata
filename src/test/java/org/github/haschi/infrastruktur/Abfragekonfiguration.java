package org.github.haschi.infrastruktur;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.github.haschi.haushaltsbuch.InventarProjektion;
import org.picocontainer.Startable;

public class Abfragekonfiguration implements Startable {

    public CommandGateway commandGateway() {
        return konfiguration.commandGateway();
    }

    private final Configuration konfiguration;

    public Abfragekonfiguration(final EventStoreLieferant storagelieferant) {
        //Display berechnung = new Display();

        // final EventHandlingConfiguration eventHandler = new EventHandlingConfiguration()
//                .registerEventHandler(configuration -> berechnung);


        this.konfiguration = DefaultConfigurer.defaultConfiguration()
                .registerCommandHandler(configuration -> new InventarProjektion(configuration))
//                .registerModule(eventHandler)
                .configureEventStore(c -> storagelieferant.eventBus(c))
                .buildConfiguration();
    }

    @Override
    public void start() {
        konfiguration.start();
    }

    @Override
    public void stop() {
        konfiguration.shutdown();
    }
}
