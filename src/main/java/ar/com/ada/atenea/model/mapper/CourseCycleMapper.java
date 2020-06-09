package ar.com.ada.atenea.model.mapper;

import ar.com.ada.atenea.model.dto.CourseDTO;
import ar.com.ada.atenea.model.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseCycleMapper extends DataCycleMapper<CourseDTO, Course>{

    CourseCycleMapper MAPPER = Mappers.getMapper(CourseCycleMapper.class);

}
