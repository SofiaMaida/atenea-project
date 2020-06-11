package ar.com.ada.atenea.model.mapper;

import ar.com.ada.atenea.model.dto.RepresentativeDTO;
import ar.com.ada.atenea.model.entity.Representative;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RepresentativeCycleMapper extends DataCycleMapper<RepresentativeDTO, Representative> {

    RepresentativeCycleMapper MAPPER = Mappers.getMapper(RepresentativeCycleMapper.class);

    @Mapping(target = "companyId", ignore = true)
    RepresentativeDTO toDto(Representative entity, CycleAvoidingMappingContext context);

}
