package ar.com.ada.atenea.service;

import ar.com.ada.atenea.component.BusinessLogicExceptionComponent;
import ar.com.ada.atenea.model.dto.CourseDTO;
import ar.com.ada.atenea.model.entity.Company;
import ar.com.ada.atenea.model.entity.Course;
import ar.com.ada.atenea.model.mapper.CourseCycleMapper;
import ar.com.ada.atenea.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.atenea.model.repository.CompanyRepository;
import ar.com.ada.atenea.model.repository.CourseRepository;
import ar.com.ada.atenea.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service("courseServices")
public class CourseServices implements Services<CourseDTO> {

    @Autowired @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired @Qualifier("courseRepository")
    private CourseRepository courseRepository;

    @Autowired @Qualifier("companyRepository")
    private CompanyRepository companyRepository;

    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private CourseCycleMapper courseCycleMapper = CourseCycleMapper.MAPPER;

    @Override
    public List<CourseDTO> findAll() {
        List<Course> all = courseRepository.findAll();
        List<CourseDTO> courseDTOList = courseCycleMapper.toDto(all, context);
        return courseDTOList;
    }


    public CourseDTO findCourseById(Long id) {
        Optional<Course> byIdOptional = courseRepository.findById(id);
        CourseDTO courseDTO = null;

        if (byIdOptional.isPresent()) {
            Course courseById = byIdOptional.get();
            courseDTO = courseCycleMapper.toDto(courseById, context);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Course", id);
        }
        return courseDTO;
    }

    @Override
    public CourseDTO save(CourseDTO dto) {
        Long companyId = dto.getCompanyId();
        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("Companny", companyId));

        Course courseToSave = courseCycleMapper.toEntity(dto, context);
        courseToSave.setCompany(company);
        Course courseSaved = courseRepository.save(courseToSave);
        CourseDTO courseDTOSaved = courseCycleMapper.toDto(courseSaved, context);
        return courseDTOSaved;
    }


    public CourseDTO updateCourse(CourseDTO courseDtoToUpdate, Long id) {
        Optional<Course> byIdOptional = courseRepository.findById(id);
        CourseDTO courseDtoUpdated = null;

        if (byIdOptional.isPresent()) {
            Course courseById = byIdOptional.get();
            courseDtoToUpdate.setId(courseById.getId());
            Course courseToUpdate = courseCycleMapper.toEntity(courseDtoToUpdate, context);
            Course courseUpdated = courseRepository.save(courseToUpdate);
            courseDtoUpdated = courseCycleMapper.toDto(courseUpdated, context);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Course", id);
        }
        return courseDtoUpdated;
    }


    @Override
    public void delete(Long id) {
        Optional<Course> byIdOptional = courseRepository.findById(id);

        if(byIdOptional.isPresent()) {
            Course courseToDelete = byIdOptional.get();
            courseRepository.delete(courseToDelete);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Course", id);
        }

    }
}
