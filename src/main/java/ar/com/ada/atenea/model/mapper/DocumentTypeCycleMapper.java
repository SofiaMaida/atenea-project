package ar.com.ada.atenea.model.mapper;

import ar.com.ada.atenea.model.dto.DocumentTypeDTO;
import ar.com.ada.atenea.model.entity.DocumentType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DocumentTypeCycleMapper extends DataCycleMapper<DocumentTypeDTO, DocumentType> {

    DocumentTypeCycleMapper MAPPER = Mappers.getMapper(DocumentTypeCycleMapper.class);

}
