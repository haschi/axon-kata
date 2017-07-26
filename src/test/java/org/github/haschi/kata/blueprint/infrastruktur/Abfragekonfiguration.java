package org.github.haschi.kata.blueprint.infrastruktur;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.config.EventHandlingConfiguration;
import org.github.haschi.kata.blueprint.taschenrechner.abfrage.Display;
import org.picocontainer.Startable;

public class Abfragekonfiguration implements Startable {

    public CommandGateway commandGateway() {
        return konfiguration.commandGateway();
    }

    private final Configuration konfiguration;

    public Abfragekonfiguration(final EventStoreLieferant storagelieferant) {
        Display berechnung = new Display();

        final EventHandlingConfiguration eventHandler = new EventHandlingConfiguration()
                .registerEventHandler(configuration -> berechnung);


        this.konfiguration = DefaultConfigurer.defaultConfiguration()
                .registerCommandHandler(configuration -> berechnung)
                .registerModule(eventHandler)
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
