package ar.com.ada.atenea.service;

import ar.com.ada.atenea.component.BusinessLogicExceptionComponent;
import ar.com.ada.atenea.model.dto.ParticipantsDTO;
import ar.com.ada.atenea.model.entity.Participant;
import ar.com.ada.atenea.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.atenea.model.mapper.ParticipantCycleMapper;
import ar.com.ada.atenea.model.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("participantServices")
public class ParticipantServices implements Services<ParticipantsDTO>{

    @Autowired @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired @Qualifier("participantRepository")
    private ParticipantRepository participantRepository;

    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private ParticipantCycleMapper participantCycleMapper = ParticipantCycleMapper.MAPPER;

    @Override
    public List<ParticipantsDTO> findAll() {
        List<Participant> participantsEntityList = participantRepository.findAll();
        List<ParticipantsDTO> participantsDTOList = participantCycleMapper.toDto(participantsEntityList, context);
        return participantsDTOList;
    }

    public ParticipantsDTO findParticipantsById(Long id) {
        // SELECT * FROM Participant WHERE id = ?
        Optional<Participant> byIdOptional = participantRepository.findById(id);
        ParticipantsDTO participantsDTO = null;

        if (byIdOptional.isPresent()) {
            Participant participantById = byIdOptional.get();
            participantsDTO = participantCycleMapper.toDto(participantById, context);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Participant", id);
        }
        return participantsDTO;
    }


    @Override
    public ParticipantsDTO save(ParticipantsDTO dto) {
        Participant participantToSave = participantCycleMapper.toEntity(dto, context);
        Participant participantSaved = participantRepository.save(participantToSave);
        ParticipantsDTO participantDtoSaved = participantCycleMapper.toDto(participantSaved, context);
        return participantDtoSaved;
    }

    public ParticipantsDTO updateParticipant(ParticipantsDTO participantDtoToUpdate, Long id) {
        Optional<Participant> byIdOptional = participantRepository.findById(id);
        ParticipantsDTO participantDtoUpdated = null;

        if (byIdOptional.isPresent()) {
            Participant participantById = byIdOptional.get();
            participantDtoToUpdate.setId(participantById.getId());
            Participant participantToUpdate = participantCycleMapper.toEntity(participantDtoToUpdate, context);
            Participant participantUpdated = participantRepository.save(participantToUpdate);
            participantDtoUpdated = participantCycleMapper.toDto(participantUpdated, context);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Participant", id);
        }
        return participantDtoUpdated;
    }


    @Override
    public void delete(Long id) {
        Optional<Participant> byIdOptional = participantRepository.findById(id);

        if (byIdOptional.isPresent()) {
            Participant participantToDelete = byIdOptional.get();
            participantRepository.delete(participantToDelete);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Participant", id);
        }
    }
}
