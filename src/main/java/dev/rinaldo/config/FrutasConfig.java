package dev.rinaldo.config;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class FrutasConfig {

    private boolean simularEspera;
    private boolean simularExcecao;

    @Inject
    public FrutasConfig(
            @ConfigProperty(name = "frutas.simular-espera", defaultValue = "false") boolean simularEspera,
            @ConfigProperty(name = "frutas.simular-excecao", defaultValue = "false") boolean simularExcecao) {
        this.simularEspera = simularEspera;
        this.simularExcecao = simularExcecao;
    }

    public boolean isSimularEspera() {
        return simularEspera;
    }

    public boolean isSimularExcecao() {
        return simularExcecao;
    }

}
