package dev.rinaldo.domain;

import javax.persistence.Entity;

import org.hibernate.annotations.NaturalId;

/**
 * Exemplo de {@link Entity}.
 * 
 * Especificações utilizadas:
 * - JPA e Hibernate através das anotações {@link Entity} e {@link NaturalId}.
 * 
 * Veja a classe {@link AbstractEntity} para mais informações sobre padrões de implementação.
 * 
 * @author rinaldodev
 *
 */
@Entity
public class Fruta extends AbstractEntity {

    @NaturalId
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Fruta other = (Fruta) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

}
