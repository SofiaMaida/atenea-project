package ar.com.ada.atenea.service;

import ar.com.ada.atenea.component.BusinessLogicExceptionComponent;
import ar.com.ada.atenea.model.dto.SocioeconomicDTO;
import ar.com.ada.atenea.model.entity.SocioEconomic;
import ar.com.ada.atenea.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.atenea.model.mapper.SocioEconomicCycleMapper;
import ar.com.ada.atenea.model.repository.SocioEconomicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("socioEconomicServices")
public class SocioEconomicServices implements Services<SocioeconomicDTO>{

    @Autowired @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired @Qualifier("socioEconomicRepository")
    private SocioEconomicRepository socioEconomicRepository;

    private SocioEconomicCycleMapper socioEconomicCycleMapper = SocioEconomicCycleMapper.MAPPER;

    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;


    @Override
    public List<SocioeconomicDTO> findAll() {
        List<SocioEconomic> all = socioEconomicRepository.findAll();
        List<SocioeconomicDTO> socioeconomicDTOList = socioEconomicCycleMapper.toDto(all, context);
        return socioeconomicDTOList;
    }

    @Override
    public SocioeconomicDTO save(SocioeconomicDTO dto) {
        SocioEconomic socioEconomicToSave = socioEconomicCycleMapper.toEntity(dto, context);
        SocioEconomic socioEconomicSaved = socioEconomicRepository.save(socioEconomicToSave);
        SocioeconomicDTO socioeconomicDTOSaved = socioEconomicCycleMapper.toDto(socioEconomicSaved, context);
        return socioeconomicDTOSaved;
    }

    @Override
    public void delete(Long id) {
    }
}
