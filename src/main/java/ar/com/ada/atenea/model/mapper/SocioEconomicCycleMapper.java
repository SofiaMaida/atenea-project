package ar.com.ada.atenea.model.mapper;

import ar.com.ada.atenea.model.dto.SocioeconomicDTO;
import ar.com.ada.atenea.model.entity.SocioEconomic;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SocioEconomicCycleMapper extends DataCycleMapper<SocioeconomicDTO, SocioEconomic>{

    SocioEconomicCycleMapper MAPPER = Mappers.getMapper(SocioEconomicCycleMapper.class);

}
