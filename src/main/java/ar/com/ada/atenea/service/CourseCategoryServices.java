package ar.com.ada.atenea.service;

import ar.com.ada.atenea.component.BusinessLogicExceptionComponent;
import ar.com.ada.atenea.model.dto.CourseCategoryDTO;
import ar.com.ada.atenea.model.entity.CourseCategory;
import ar.com.ada.atenea.model.mapper.CourseCategoryCycleMapper;
import ar.com.ada.atenea.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.atenea.model.repository.CourseCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("courseCategoryServices")
public class CourseCategoryServices implements Services<CourseCategoryDTO> {

    @Autowired @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired @Qualifier("courseCategoryRepository")
    private CourseCategoryRepository courseCategoryRepository;

    private CourseCategoryCycleMapper courseCategoryCycleMapper = CourseCategoryCycleMapper.MAPPER;

    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    @Override
    public List<CourseCategoryDTO> findAll() {
        List<CourseCategory> all = courseCategoryRepository.findAll();
        List<CourseCategoryDTO> courseCategoryDTOList = courseCategoryCycleMapper.toDto(all, context);
        return courseCategoryDTOList;
    }

    @Override
    public CourseCategoryDTO save(CourseCategoryDTO dto) {
        CourseCategory courseCategoryToSave = courseCategoryCycleMapper.toEntity(dto, context);
        CourseCategory courseCategorySaved = courseCategoryRepository.save(courseCategoryToSave);
        CourseCategoryDTO courseCategoryDTOSaved = courseCategoryCycleMapper.toDto(courseCategorySaved, context);
        return courseCategoryDTOSaved;
    }

    @Override
    public void delete(Long id) {
        Optional<CourseCategory> byIdOptional = courseCategoryRepository.findById(id);

        if (byIdOptional.isPresent()) {
            CourseCategory courseCategoryToDelete = byIdOptional.get();
            courseCategoryRepository.delete(courseCategoryToDelete);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Course Category", id);
        }

    }
}
