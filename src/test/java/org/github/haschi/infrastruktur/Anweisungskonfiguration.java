package org.github.haschi.infrastruktur;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.github.haschi.haushaltsbuch.Inventur;
import org.picocontainer.Startable;

public class Anweisungskonfiguration implements Startable {

    public CommandGateway commandGateway() {
        return konfiguration.commandGateway();
    }

    private final Configuration konfiguration;

    public Anweisungskonfiguration(final EventStoreLieferant storagelieferant) {
        this.konfiguration = DefaultConfigurer.defaultConfiguration()
                .configureAggregate(Inventur.class)
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
