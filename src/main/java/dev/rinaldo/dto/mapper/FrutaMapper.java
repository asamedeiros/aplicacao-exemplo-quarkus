package dev.rinaldo.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import dev.rinaldo.domain.Fruta;
import dev.rinaldo.dto.FrutaDTO;

@Mapper(componentModel = "cdi")
public interface FrutaMapper {

    FrutaDTO toResource(Fruta fruta);

    List<FrutaDTO> toResourceList(List<Fruta> frutas);

}
