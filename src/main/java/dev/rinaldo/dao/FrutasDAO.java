package dev.rinaldo.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import dev.rinaldo.domain.Fruta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class FrutasDAO implements PanacheRepository<Fruta> {

    public List<Fruta> findByNome(String nome) {
        return list("nome", nome);
    }

    public List<Fruta> findMaisVotadas() {
        return find("order by votos desc").page(0, 3).list();
    }

}
