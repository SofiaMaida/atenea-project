package ar.com.ada.atenea.service;

import ar.com.ada.atenea.component.BusinessLogicExceptionComponent;
import ar.com.ada.atenea.model.dto.SocioEconomicDTO;
import ar.com.ada.atenea.model.entity.Participant;
import ar.com.ada.atenea.model.entity.SocioEconomic;
import ar.com.ada.atenea.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.atenea.model.mapper.SocioEconomicCycleMapper;
import ar.com.ada.atenea.model.repository.ParticipantRepository;
import ar.com.ada.atenea.model.repository.SocioEconomicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service("socioEconomicServices")
public class SocioEconomicServices implements Services<SocioEconomicDTO>{

    @Autowired @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired @Qualifier("socioEconomicRepository")
    private SocioEconomicRepository socioEconomicRepository;

    @Autowired @Qualifier("participantRepository")
    private ParticipantRepository participantRepository;

    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private SocioEconomicCycleMapper socioEconomicCycleMapper = SocioEconomicCycleMapper.MAPPER;

    @Override
    public List<SocioEconomicDTO> findAll() {
        List<SocioEconomic> all = socioEconomicRepository.findAll();
        List<SocioEconomicDTO> socioEconomicDTOList = socioEconomicCycleMapper.toDto(all, context);
        return socioEconomicDTOList;
    }

    @Override
    public SocioEconomicDTO save(SocioEconomicDTO dto) {
        Long participantId = dto.getParticipantId();
        Participant participant = participantRepository
                .findById(participantId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("Participant", participantId));

        SocioEconomic socioEconomicToSave = socioEconomicCycleMapper.toEntity(dto, context);
        socioEconomicToSave.setParticipant(participant);
        SocioEconomic socioEconomicSaved = socioEconomicRepository.save(socioEconomicToSave);
        SocioEconomicDTO socioEconomicDTOSaved = socioEconomicCycleMapper.toDto(socioEconomicSaved, context);
        return socioEconomicDTOSaved;
    }

    @Override
    public void delete(Long id) {
    }
}
