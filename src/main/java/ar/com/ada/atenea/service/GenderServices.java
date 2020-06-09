package ar.com.ada.atenea.service;

import ar.com.ada.atenea.component.BusinessLogicExceptionComponent;
import ar.com.ada.atenea.model.dto.GenderDTO;
import ar.com.ada.atenea.model.entity.Gender;
import ar.com.ada.atenea.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.atenea.model.mapper.GenderCycleMapper;
import ar.com.ada.atenea.model.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("genderServices")
public class GenderServices implements Services<GenderDTO>{

    @Autowired @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    @Autowired @Qualifier("genderRepository")
    private GenderRepository genderRepository;

    private GenderCycleMapper genderCycleMapper = GenderCycleMapper.MAPPER;

    @Override
    public List<GenderDTO> findAll() {
        List<Gender> genderEntityList = genderRepository.findAll();
        List<GenderDTO> genderDTOList = genderCycleMapper.toDto(genderEntityList, context);
        return genderDTOList;
    }

    @Override
    public GenderDTO save(GenderDTO dto) {
        Gender genderToSave = genderCycleMapper.toEntity(dto, context);
        Gender genderSaved = genderRepository.save(genderToSave);
        GenderDTO genderDTOSaved = genderCycleMapper.toDto(genderSaved, context);
        return genderDTOSaved;
    }

    @Override
    public void delete(Long id) {
    }
}
