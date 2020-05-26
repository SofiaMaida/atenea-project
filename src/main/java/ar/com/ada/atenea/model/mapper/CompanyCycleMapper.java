package ar.com.ada.atenea.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyCycleMapper extends DataCycleMapper<CompanyDTO, Company> {

    CompanyCycleMapper MAPPER = Mappers.getMapper(CompanyCycleMapper.class);

}
