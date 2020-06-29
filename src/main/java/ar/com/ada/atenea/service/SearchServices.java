package ar.com.ada.atenea.service;

import ar.com.ada.atenea.component.BusinessLogicExceptionComponent;
import ar.com.ada.atenea.model.dto.CourseDTO;
import ar.com.ada.atenea.model.entity.Course;
import ar.com.ada.atenea.model.mapper.CourseCycleMapper;
import ar.com.ada.atenea.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.atenea.model.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("searchServices")
public class SearchServices {

    @Autowired @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired @Qualifier("courseRepository")
    private CourseRepository courseRepository;

    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private CourseCycleMapper courseCycleMapper = CourseCycleMapper.MAPPER;

    public List<CourseDTO> getAllCourseAvailable(Integer page) {
        Page<Course> allCourseAvailable = courseRepository
                .findAllCourseAvailable(PageRequest.of(page, 2, Sort.Direction.ASC, "id"));

        List<Course> courseList = allCourseAvailable.getContent();
        List<CourseDTO> courseDTOSList = courseCycleMapper.toDto(courseList, context);

        return courseDTOSList;
    }

    public List<CourseDTO> getAllCourseByCompany(Long companyId, Integer page) {
        Page<Course> allByCompanyPage = courseRepository
                .findAllByCompany(companyId, PageRequest.of(page, 2, Sort.Direction.ASC, "id"));

        List<Course> allByCompany = allByCompanyPage.getContent();
        List<CourseDTO> allByCompanyListDto = courseCycleMapper.toDto(allByCompany, context);

        return allByCompanyListDto;
    }

    public List<CourseDTO> getAllCourseByCompanyAndCategory(Long companyId, Long categoryCourseId,Integer page) {
        Page<Course> allByCompanyAndCategoryPage = courseRepository
                .findAllByCompanyAndCategory(companyId, categoryCourseId, PageRequest.of(page, 2, Sort.Direction.ASC, "id"));

        List<Course> allByCompanyAndCategory = allByCompanyAndCategoryPage.getContent();
        List<CourseDTO> allByCompanyAndCategoryListDto = courseCycleMapper.toDto(allByCompanyAndCategory, context);

        return allByCompanyAndCategoryListDto;
    }

    public List<CourseDTO> getAllCourseProgressStatus(String hasFinish, Integer page) {
        Page<Course> allByCourseProgressStatusPage = courseRepository
                .findAllByCourseByParticipantsProgressStatus(Boolean.valueOf(hasFinish), PageRequest.of(page, 2, Sort.Direction.ASC, "id"));

        List<Course> allCourseByProgressStatus = allByCourseProgressStatusPage.getContent();
        List<CourseDTO> allByCourseStatusListDto = courseCycleMapper.toDto(allCourseByProgressStatus, context);

        return allByCourseStatusListDto;
    }

    public List<CourseDTO> findAllCourseByCategory(Integer categoryId, Integer page) {
        List<CourseDTO> courseDTOS;
        List<Course> allCourse;

        if(categoryId == null) {
            Page<Course> courseByCategory = courseRepository
                    .findAllCourseByCategory(categoryId, PageRequest.of(page, 2, Sort.Direction.ASC, "id"));

            allCourse = courseByCategory.getContent();
        } else {
            Page<Course> courseByCategory = courseRepository
                    .findAll(PageRequest.of(page, 2, Sort.Direction.ASC, "id"));

            allCourse = courseByCategory.getContent();
        }
        courseDTOS = courseCycleMapper.toDto(allCourse, context);

        return courseDTOS;
    }
}
