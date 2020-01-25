package dev.rinaldo.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import dev.rinaldo.domain.Fruit;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class FruitsDAO implements PanacheRepository<Fruit> {

    public List<Fruit> findByName(String name) {
        return list("name", name);
    }

}
