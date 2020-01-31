package dev.rinaldo.dto;

import dev.rinaldo.domain.Fruta;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

/**
 * Exemplo de DTO.
 * 
 * Especificações utilizadas:
 * - nenhuma.
 * 
 * Exemplo de uma DTO simples para trafegar os dados da entidade {@link Fruta}.
 * 
 * Perceba que não há fields privados, getters e setters. Existe uma discussão sobre como DTOs devem ser utilizadas.
 * O conceito que estou utilizando aqui é:
 * - não há getter e setter, porque não é uma classe feita para conter regras ou lógicas, nem mesmo de transformação.
 * - pode haver equals e hashCode, nesse caso deve sempre testar todos os valores, pois se é uma classe para transferir valores,
 * se qualquer um estiver diferente, as instâncias representam coisas diferentes.
 * - pode haver toString, afinal é só uma representação visual dos valores da classe.
 * - em resumo, não pode haver comportamento, mas pode haver esses métodos herdados que não representam
 * comportamento/lógica/transformação.
 * 
 * O que está pendente aqui, e é muito importante, é a falta de imutabilidade. Existe um Issue aberta falando disso.
 * 
 * @author rinaldodev
 *
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class FrutaDTO {

    private final Long id;
    private final String nome;
    private Integer votos;
  
}
