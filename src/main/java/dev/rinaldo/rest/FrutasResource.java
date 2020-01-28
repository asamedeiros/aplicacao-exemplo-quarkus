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
import dev.rinaldo.dto.FrutaDTO;
import dev.rinaldo.dto.mapper.FrutaMapper;

/**
 * Resource de Frutas.
 * 
 * Especificações utilizadas:
 * - JAX-RS e JSON-B através das anotações {@link Path}, {@link Produces}, {@link Consumes} e {@link GET}.
 * - CDI com {@link ApplicationScoped} e {@link Inject}.
 * - MicroProfile Fault Tolerance com {@link Timeout}, {@link Retry}, {@link CircuitBreaker} e {@link Fallback}.
 * 
 * Aqui todas as dependências da classe são injetadas via construtor. Isso facilita a execução de teste unitários sem CDI, pois
 * basta criar uma nova instância da classe passando Mocks como parâmetros.
 * 
 * Essa é a classe que mais deve conter testes unitários. De preferência algo próximo a 100% de cobertura.
 * 
 * É possível criar DTOs específicas para operações específicas, removendo ou acrescentando atributos que não constam na DTO que
 * representa a entidade original. Isso pode inclusive diminuir significativamente o processamento de JSON e a quantidade de
 * dados trafegados. Ainda não está feito aqui, mas há uma Issue para isso.
 * 
 * @author rinaldodev
 *
 */
@Path("/frutas")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FrutasResource {

    /**
     * vai simular espera de 1 segundo de forma alternada: uma chamada sim, outra não. Veja a utilização nos métodos ao final.
     */
    private boolean simularEspera = false;

    /**
     * vai simular uma exceção de forma alternada: uma chamada sim, outra não. Veja a utilização nos métodos ao final.
     */
    private boolean simularExcecao = false;

    /**
     * Dependências injetadas no construtor.
     */
    private final Logger logger;
    private final FrutasDAO frutasDAO;
    private final FrutasConfig frutasConfig;
    private final FrutaMapper frutaMapper;

    @Inject
    public FrutasResource(
            FrutasDAO frutasDAO,
            Logger logger,
            FrutasConfig frutasConfig,
            FrutaMapper frutaMapper) {
        this.logger = logger;
        this.frutasDAO = frutasDAO;
        this.frutasConfig = frutasConfig;
        this.frutaMapper = frutaMapper;
    }

    /**
     * Exemplo de método com Fault Tolerance.
     * 
     * O {@link Timeout} garante que, caso o método não responsa em menos de 1 segundo, lança um erro.
     * 
     * O {@link Retry} garante que, em caso de erro, o método tentará ser executado mais uma vez.
     * 
     * O {@link CircuitBreaker} garante, em caso de muito erros consecutivos, o serviço para de responder imediatamente por
     * algum tempo, enquanto se recupera.
     */
    @GET
    @Timeout(value = 1000)
    @Retry(maxRetries = 1)
    @CircuitBreaker
    public List<FrutaDTO> get() {
        talvezEspere1Seg();
        List<Fruta> frutas = frutasDAO.listAll();
        return frutaMapper.toResourceList(frutas);
    }

    /**
     * Exemplo de método com Fault Tolerance.
     * 
     * O {@link Retry} garante que, em caso de erro, o método tentará ser executado mais uma vez.
     * 
     * O {@link Fallback} garante que, em caso de erro no método, outro método será chamado no lugar. Isso ocorre apenas depois
     * do {@link Retry}.
     * 
     * O {@link CircuitBreaker} garante, em caso de muito erros consecutivos, o serviço passa a usar imediatamente o método de
     * Fallback, enquanto o método original não se recupera.
     */
    @GET
    @Path("maisVotadas")
    @Retry(maxRetries = 1) // depois de 2 tentativas (1 retry) com erro, chama o fallback
    @Fallback(fallbackMethod = "fallbackFrutasMaisVotadas") // será chamado em caso de erro
    @CircuitBreaker // depois de vários erros seguidos, fica usando só o fallback por algum tempo
    public List<FrutaDTO> getMaisVotadas() {
        logger.trace("GET frutas mais votadas.");
        talvezLanceExcecao();
        List<Fruta> maisVotadas = frutasDAO.findMaisVotadas();
        return frutaMapper.toResourceList(maisVotadas);
    }

    public List<FrutaDTO> fallbackFrutasMaisVotadas() {
        // no fallback retornamos uma Ameixa porque, mesmo sem consultar a base, sabemos que é a melhor fruta :)
        Fruta frutaMaisVotada = new Fruta();
        frutaMaisVotada.setNome("Ameixa");
        FrutaDTO dto = frutaMapper.toResource(frutaMaisVotada);
        return List.of(dto);
    }

    private void talvezLanceExcecao() {
        if (!frutasConfig.isSimularExcecao()) {
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
        if (!frutasConfig.isSimularEspera()) {
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