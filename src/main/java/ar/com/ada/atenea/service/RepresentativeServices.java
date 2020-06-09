package ar.com.ada.atenea.service;

import ar.com.ada.atenea.component.BusinessLogicExceptionComponent;
import ar.com.ada.atenea.model.dto.RepresentativeDTO;
import ar.com.ada.atenea.model.entity.Representative;
import ar.com.ada.atenea.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.atenea.model.mapper.RepresentativeCycleMapper;
import ar.com.ada.atenea.model.repository.RepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("representativeServices")
public class RepresentativeServices implements Services<RepresentativeDTO>{

    @Autowired @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired @Qualifier("representativeRepository")
    private RepresentativeRepository representativeRepository;

    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private RepresentativeCycleMapper representativeCycleMapper = RepresentativeCycleMapper.MAPPER;

    @Override
    public List<RepresentativeDTO> findAll() {
        List<Representative> representativesEntityList = representativeRepository.findAll();
        List<RepresentativeDTO> representativesDtoList = representativeCycleMapper.toDto(representativesEntityList, context);
        return representativesDtoList;
    }

    public RepresentativeDTO findRepresentativeById(Long id) {
        // SELECT * FROM Representative WHERE id = ?
        Optional<Representative> byIdOptional = representativeRepository.findById(id);
        RepresentativeDTO representativeDTO = null;

        if (byIdOptional.isPresent()) {
            Representative representativeById = byIdOptional.get();
            representativeDTO = representativeCycleMapper.toDto(representativeById, context);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Representative", id);
        }
        return representativeDTO;
    }

    @Override
    public RepresentativeDTO save(RepresentativeDTO dto) {
        Representative representativeToSave = representativeCycleMapper.toEntity(dto, context);
        Representative representativeSaved = representativeRepository.save(representativeToSave);
        RepresentativeDTO representativeDtoSaved = representativeCycleMapper.toDto(representativeSaved, context);
        return representativeDtoSaved;
    }

    public RepresentativeDTO updateRepresentative(RepresentativeDTO representativeDtoToUpdate, Long id) {
        Optional<Representative> byIdOptional = representativeRepository.findById(id);
        RepresentativeDTO representativeDtoUpdated = null;

        if (byIdOptional.isPresent()) {
            Representative representativeById = byIdOptional.get();
            representativeDtoToUpdate.setId(representativeById.getId());
            Representative representativeToUpdate = representativeCycleMapper.toEntity(representativeDtoToUpdate, context);
            Representative representativeUpdated = representativeRepository.save(representativeToUpdate);
            representativeDtoUpdated = representativeCycleMapper.toDto(representativeUpdated, context);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Representative", id);
        }
        return representativeDtoUpdated;
    }

    @Override
    public void delete(Long id) {
        Optional<Representative> byIdOptional = representativeRepository.findById(id);

        if (byIdOptional.isPresent()) {
            Representative representativeToDelete = byIdOptional.get();
            representativeRepository.delete(representativeToDelete);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Representative", id);
        }
    }
}
