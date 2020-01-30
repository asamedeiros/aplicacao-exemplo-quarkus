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
public class FrutaDTO {

    public Long id;
    public String nome;
    public Integer votos;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((votos == null) ? 0 : votos.hashCode());
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
        FrutaDTO other = (FrutaDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (votos == null) {
            if (other.votos != null)
                return false;
        } else if (!votos.equals(other.votos))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FrutaDTO [id=" + id + ", nome=" + nome + ", votos=" + votos + "]";
    }

}
