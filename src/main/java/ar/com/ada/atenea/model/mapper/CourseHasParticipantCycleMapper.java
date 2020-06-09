package ar.com.ada.atenea.model.mapper;

import ar.com.ada.atenea.model.dto.CourseHasParticipantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseHasParticipantCycleMapper extends DataCycleMapper<CourseHasParticipantCycleMapper, CourseHasParticipantDTO>{

    CourseHasParticipantCycleMapper MAPPER = Mappers.getMapper(CourseHasParticipantCycleMapper.class);

}
