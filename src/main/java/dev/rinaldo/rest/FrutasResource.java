package dev.rinaldo.rest;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.slf4j.Logger;

import dev.rinaldo.config.FrutasConfig;
import dev.rinaldo.dao.FrutasDAO;
import dev.rinaldo.domain.Fruta;

@Path("/frutas")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FrutasResource {

    private boolean simularEspera = false;
    private boolean simularExcecao = false;

    private final Logger logger;
    private final FrutasDAO frutasDAO;
    private final FrutasConfig frutasConfig;

    @Inject
    public FrutasResource(
            FrutasDAO frutasDAO,
            Logger logger,
            FrutasConfig frutasConfig) {
        this.logger = logger;
        this.frutasDAO = frutasDAO;
        this.frutasConfig = frutasConfig;
    }

    @GET
    @Timeout(value = 1000) // se não responder em 1 segundo, lança erro
    @Retry(maxRetries = 1) // depois de 2 tentativas (1 retry) com erro, retorna erro
    @CircuitBreaker // depois de vários erros seguidos, retorna erro imediatamente por algum tempo
    public List<Fruta> get() {
        talvezEspere1Seg();
        return frutasDAO.listAll();
    }

    @GET
    @Path("maisVotadas")
    @Retry(maxRetries = 1) // depois de 2 tentativas (1 retry) com erro, chama o fallback
    @Fallback(fallbackMethod = "fallbackFrutasMaisVotadas") // será chamado em caso de erro
    @CircuitBreaker // depois de vários erros seguidos, fica usando só o fallback por algum tempo
    public List<Fruta> getMaisVotadas() {
        logger.trace("GET frutas mais votadas.");
        talvezLanceExcecao();
        return frutasDAO.findMaisVotadas();
    }

    public List<Fruta> fallbackFrutasMaisVotadas() {
        // no fallback retornamos uma Ameixa porque, mesmo sem consultar a base, sabemos que é a melhor fruta :)
        Fruta e1 = new Fruta();
        e1.setNome("Ameixa");
        return List.of(e1);
    }

    private void talvezLanceExcecao() {
        if (!frutasConfig.simularExcecao.orElse(Boolean.FALSE)) {
            return;
        }

        if (simularExcecao) {
            simularExcecao = false;
            logger.error("Simulando Excecao!");
            throw new RuntimeException("Erro!");
        } else {
            simularExcecao = true;
        }
    }

    private void talvezEspere1Seg() {
        if (!frutasConfig.simularEspera.orElse(Boolean.FALSE)) {
            return;
        }

        if (simularEspera) {
            simularEspera = false;
            logger.error("Simulando espera de 1 segundo!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            simularEspera = true;
        }
    }

}