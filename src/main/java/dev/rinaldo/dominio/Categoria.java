package dev.rinaldo.dominio;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.NaturalId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Exemplo de {@link Entity}.
 * 
 * @see Fruta
 * 
 * @author rinaldodev
 */
@Entity
@Data
@ToString
@EqualsAndHashCode(of = "nome", callSuper = false)
public class Categoria extends AbstractEntidade {

    @NaturalId
    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

}
