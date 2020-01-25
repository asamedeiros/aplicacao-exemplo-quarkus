package dev.rinaldo.rest;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.Retry;

import dev.rinaldo.dao.FruitsDAO;
import dev.rinaldo.domain.Fruit;

@Path("/fruits")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitsResource {

    private final FruitsDAO fruitDAO;

    @Inject
    public FruitsResource(FruitsDAO fruitDAO) {
        this.fruitDAO = fruitDAO;
    }

    @GET
    @Retry(maxRetries = 3)
    public List<Fruit> list() {
        return fruitDAO.listAll();
    }

    @GET
    public List<Fruit> getByName(@QueryParam("name") String name) {
        return fruitDAO.findByName(name);
    }

}