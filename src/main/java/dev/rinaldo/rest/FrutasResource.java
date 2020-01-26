package dev.rinaldo.rest;

import java.util.List;
import java.util.Random;

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

import dev.rinaldo.dao.FrutasDAO;
import dev.rinaldo.domain.Fruta;

@Path("/frutas")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FrutasResource {

    private final Logger logger;
    private final FrutasDAO frutasDAO;

    @Inject
    public FrutasResource(FrutasDAO frutasDAO, Logger logger) {
        this.logger = logger;
        this.frutasDAO = frutasDAO;
    }

    @GET
    @Timeout(value = 1000)
    @Retry(maxRetries = 1)
    @CircuitBreaker
    public List<Fruta> get() {
        talvezEspere1Seg();
        return frutasDAO.listAll();
    }

    @GET
    @Path("maisVotadas")
    @Retry(maxRetries = 1) // depois de 2 tentativas (1 retry) chama o fallback
    @Fallback(fallbackMethod = "fallbackFrutasMaisVotadas")
    @CircuitBreaker
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
        // 50% de chance de lançar exceção!
        double draw = new Random().nextDouble();
        if (draw > 0.5) {
            logger.error("Erro foi sorteado! {}", draw);
            throw new RuntimeException("Erro!");
        }
    }

    private void talvezEspere1Seg() {
        // 50% de chance de ficar parado por 1 segundo!
        double draw = new Random().nextDouble();
        if (draw > 0.5) {
            logger.error("Sleep foi sorteado! {}", draw);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}