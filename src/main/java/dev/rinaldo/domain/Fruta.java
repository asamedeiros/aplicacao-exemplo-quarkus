package dev.rinaldo.domain;

import javax.persistence.Entity;

@Entity
public class Fruta extends AbstractEntity {

    private String nome;

    private Integer votos;

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public Integer getVotos() {
        return votos;
    }

    public void setVotos(Integer votos) {
        this.votos = votos;
    }

}