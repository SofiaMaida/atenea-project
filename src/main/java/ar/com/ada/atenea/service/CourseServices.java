package ar.com.ada.atenea.service;

import ar.com.ada.atenea.component.BusinessLogicExceptionComponent;
import ar.com.ada.atenea.model.dto.CourseDTO;
import ar.com.ada.atenea.model.entity.Company;
import ar.com.ada.atenea.model.entity.Course;
import ar.com.ada.atenea.model.entity.CourseCategory;
import ar.com.ada.atenea.model.mapper.CourseCycleMapper;
import ar.com.ada.atenea.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.atenea.model.repository.CompanyRepository;
import ar.com.ada.atenea.model.repository.CourseCategoryRepository;
import ar.com.ada.atenea.model.repository.CourseRepository;
import ar.com.ada.atenea.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service("courseServices")
public class CourseServices {

    @Autowired @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired @Qualifier("courseRepository")
    private CourseRepository courseRepository;

    @Autowired @Qualifier("courseCategoryRepository")
    private CourseCategoryRepository courseCategoryRepository;

    @Autowired @Qualifier("companyRepository")
    private CompanyRepository companyRepository;

    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private CourseCycleMapper courseCycleMapper = CourseCycleMapper.MAPPER;

    public CourseDTO save(CourseDTO dto) {
        Long companyId = dto.getCompanyId();
        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("Companny", companyId));

        Long courseCategoryId = dto.getCourseCategory().getId();
        CourseCategory courseCategory = courseCategoryRepository
                .findById(courseCategoryId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("CourseCategory", courseCategoryId));

        Course courseToSave = courseCycleMapper.toEntity(dto, context);
        courseToSave.setCompany(company);
        courseToSave.setCourseCategory(courseCategory);

        // se calcula la cantidad de cupos para la comprar
        Integer amountBuy = courseToSave.getAmountParticipants() - courseToSave.getAmountScholarship();

        // se setea esa cantidad
        courseToSave.setParticipantsCounter(amountBuy);

        // se setea la cantidad de cupos para becas
        courseToSave.setScholarshipCounter(courseToSave.getAmountScholarship());

        //if (courseToSave.getParticipantsCounter() < 0) {
        //    courseToSave.setParticipantsCounter(courseToSave.getParticipantsCounter() - 1); // compra directa
        //} else {
        //  throw new RuntimeException("No hay cupos para comprar");
        //}

        //if (courseToSave.getScholarshipCounter() < 0) {
        //    courseToSave.setScholarshipCounter(courseToSave.getScholarshipCounter() - 1); // admin aprueba
        //} else {
        //  throw new RuntimeException("No hay cupos para becas");
        //}

        //} else {
        //no hay cupos
        //logicExceptionComponent.throwExceptionSoldOut("Course", course);
        //}

        Course courseSaved = courseRepository.save(courseToSave);
        CourseDTO courseDTOSaved = courseCycleMapper.toDto(courseSaved, context);

        return courseDTOSaved;
    }

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
