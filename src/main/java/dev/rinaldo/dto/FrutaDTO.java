package dev.rinaldo.dto;

import dev.rinaldo.domain.Fruta;

/**
 * Exemplo de DTO.
 * 
 * Especificações utilizadas:
 * - nenhuma.
 * 
 * Exemplo de uma DTO simples para trafegar os dados da entidade {@link Fruta}.
 * 
 * Perceba que não é fields privados, getters, setters ou equals/hashCode. Existe uma discussão sobre como DTOs devem ser
 * utilizadas. Aqui ela está da forma mais pura possível, feita exclusivamente para servir de transferidoda de dados. Dessa
 * forma, não há necessidade desses métodos. Essa me parece a melhor forma de utizar.
 * 
 * O que está pendente aqui, e é muito importante, é a falta de imutabilidade. Existe um Issue aberta falando disso.
 * 
 * @author rinaldodev
 *
 */
public class FrutaDTO {

    public Long id;
    public String nome;
    public Integer votos;

}
