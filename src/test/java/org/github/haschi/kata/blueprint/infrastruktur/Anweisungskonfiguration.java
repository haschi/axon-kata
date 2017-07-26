package org.github.haschi.kata.blueprint.infrastruktur;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.github.haschi.kata.blueprint.taschenrechner.domaene.Taschenrechner;
import org.picocontainer.Startable;

public class Anweisungskonfiguration implements Startable {

    public CommandGateway commandGateway() {
        return konfiguration.commandGateway();
    }

    private final Configuration konfiguration;

    public Anweisungskonfiguration(final EventStoreLieferant storagelieferant) {
        this.konfiguration = DefaultConfigurer.defaultConfiguration()
                .configureAggregate(Taschenrechner.class)
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
