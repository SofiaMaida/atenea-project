package ar.com.ada.atenea.model.mapper;

import ar.com.ada.atenea.model.dto.CourseHasParticipantDTO;
import ar.com.ada.atenea.model.entity.CourseHasParticipant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseHasParticipantCycleMapper extends DataCycleMapper<CourseHasParticipantDTO, CourseHasParticipant>{

    CourseHasParticipantCycleMapper MAPPER = Mappers.getMapper(CourseHasParticipantCycleMapper.class);

}
