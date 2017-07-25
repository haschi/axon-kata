package org.github.haschi.kata.blueprint.infrastruktur;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.picocontainer.Startable;

public class Abfragekonfiguration implements Startable {

    public CommandGateway commandGateway() {
        return konfiguration.commandGateway();
    }

    private final Configuration konfiguration;

    public Abfragekonfiguration(final Storagelieferant storagelieferant) {
        this.konfiguration = DefaultConfigurer.defaultConfiguration()
                .configureEmbeddedEventStore(c -> storagelieferant.storageEngine())
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
