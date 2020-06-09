package ar.com.ada.atenea.model.mapper;

import ar.com.ada.atenea.model.dto.GenderDTO;
import ar.com.ada.atenea.model.entity.Gender;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GenderCycleMapper extends DataCycleMapper<GenderDTO, Gender> {

    GenderCycleMapper MAPPER = Mappers.getMapper(GenderCycleMapper.class);

}
