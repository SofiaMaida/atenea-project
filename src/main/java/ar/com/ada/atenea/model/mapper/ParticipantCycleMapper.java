package ar.com.ada.atenea.model.mapper;

import ar.com.ada.atenea.model.dto.ParticipantsDTO;
import ar.com.ada.atenea.model.entity.Participant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParticipantCycleMapper extends DataCycleMapper<ParticipantsDTO, Participant>{

    ParticipantCycleMapper MAPPER = Mappers.getMapper(ParticipantCycleMapper.class);

}
