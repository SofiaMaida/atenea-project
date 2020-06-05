package ar.com.ada.atenea.model.mapper;

import ar.com.ada.atenea.model.dto.CompanyDTO;
import ar.com.ada.atenea.model.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//@Mapper(componentModel = "spring")
public interface CompanyCycleMapper extends DataCycleMapper<CompanyDTO, Company> {

    //  CompanyCycleMapper MAPPER = Mappers.getMapper(CompanyCycleMapper.class);

}
