package dev.rinaldo.config;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class FrutasConfig {

    public final Optional<Boolean> simularEspera;
    public final Optional<Boolean> simularExcecao;

    // usando construtor garantimos que os fields são FINAL
    @Inject
    public FrutasConfig(
            @ConfigProperty(name = "frutas.simular-espera") Optional<Boolean> simularEspera,
            @ConfigProperty(name = "frutas.simular-excecao") Optional<Boolean> simularTimeout) {
        this.simularEspera = simularEspera;
        this.simularExcecao = simularTimeout;
    }

}
