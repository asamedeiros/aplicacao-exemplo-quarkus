package dev.rinaldo.dto;

import lombok.Builder;
import lombok.Value;

/**
 * @see FrutaDTO
 * 
 * @author rinaldodev
 *
 */
@Value
@Builder
public class CategoriaDTO {

    Long id;
    String nome;
    String descricao;

}
