package ar.com.ada.atenea.model.mapper;

import ar.com.ada.atenea.model.dto.CourseCategoryDTO;
import ar.com.ada.atenea.model.entity.CourseCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseCategoryCycleMapper extends DataCycleMapper<CourseCategoryDTO, CourseCategory>{

    CourseCategoryCycleMapper MAPPER = Mappers.getMapper(CourseCategoryCycleMapper.class);

}
