package ar.com.ada.atenea.service;

import ar.com.ada.atenea.component.BusinessLogicExceptionComponent;
import ar.com.ada.atenea.model.dto.DocumentTypeDTO;
import ar.com.ada.atenea.model.entity.DocumentType;
import ar.com.ada.atenea.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.atenea.model.mapper.DocumentTypeCycleMapper;
import ar.com.ada.atenea.model.repository.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("documentTypeServices")
public class DocumentTypeServices implements Services<DocumentTypeDTO>{

    @Autowired @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired @Qualifier("documentTypeRepository")
    private DocumentTypeRepository documentTypeRepository;

    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private DocumentTypeCycleMapper documentTypeCycleMapper = DocumentTypeCycleMapper.MAPPER;

    @Override
    public List<DocumentTypeDTO> findAll() {
        List<DocumentType> all = documentTypeRepository.findAll();
        List<DocumentTypeDTO> documentTypeDTOList = documentTypeCycleMapper.toDto(all, context);
        return documentTypeDTOList;
    }

    @Override
    public DocumentTypeDTO save(DocumentTypeDTO dto) {
        DocumentType documentTypeToSave = documentTypeCycleMapper.toEntity(dto, context);
        DocumentType documentSaved = documentTypeRepository.save(documentTypeToSave);
        DocumentTypeDTO documentTypeDtoSaved = documentTypeCycleMapper.toDto(documentSaved, context);
        return documentTypeDtoSaved;
    }

    @Override
    public void delete(Long id) {
    }
}
