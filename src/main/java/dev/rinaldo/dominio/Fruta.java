package dev.rinaldo.dominio;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Exemplo de {@link Entity}.
 * 
 * Especificações utilizadas:
 * - JPA e Hibernate através das anotações {@link Entity} e {@link NaturalId}.
 * 
 * Veja a classe {@link AbstractEntidade} para mais informações sobre padrões de implementação.
 * 
 * Para este exemplo, consideramos que nome é um ID natural, e que não pode ser alterado. Provavelmente na sua aplicação de
 * negócio haverá uma chave negocial mais relevante do que isso. :)
 * 
 * @author rinaldodev
 *
 */
@Entity
@Data
@ToString
@EqualsAndHashCode(of = "nome", callSuper = false)
public class Fruta extends AbstractEntidade {

    @NaturalId
    @NotBlank
    private String nome;

    private Integer votos;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotNull
    private Categoria categoria;

}
