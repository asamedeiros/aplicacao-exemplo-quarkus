package dev.rinaldo.domain;

import javax.persistence.Entity;

@Entity
public class Fruit extends AbstractEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}