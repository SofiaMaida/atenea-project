package ar.com.ada.atenea.controller;

import ar.com.ada.atenea.model.dto.CourseCategoryDTO;
import ar.com.ada.atenea.model.entity.CourseCategory;
import ar.com.ada.atenea.model.repository.CourseCategoryRepository;
import ar.com.ada.atenea.service.CourseCategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courseCategories")
public class CourseCategoryController {

    @Autowired @Qualifier("courseCategoryServices")
    private CourseCategoryServices courseCategoryServices;

    @Autowired @Qualifier("courseCategoryRepository")
    private CourseCategoryRepository courseCategoryRepository;

    @GetMapping({"", "/"})
    public Page<CourseCategory> getAllCourseCategory(@RequestParam Optional<String> typeCategory,
                                                     @RequestParam Optional<Integer> page,
                                                     @RequestParam Optional<String> sortBy) {
        //List<CourseCategoryDTO> all = courseCategoryServices.findAll();
        return courseCategoryRepository.findByCategory(typeCategory.orElse("_"),
                PageRequest.of(page.orElse(0), 5,
                        Sort.Direction.ASC, sortBy.orElse("id")));
    }

    @PostMapping({"", "/"})
    public ResponseEntity addNewCourseCategory(@Valid @RequestBody CourseCategoryDTO courseCategoryDTO) throws URISyntaxException {
        CourseCategoryDTO saved = courseCategoryServices.save(courseCategoryDTO);
        return ResponseEntity.created(new URI("/courseCategories/" + courseCategoryDTO.getId())).body(saved);
    }

    @DeleteMapping({"", "/"})
    public ResponseEntity deleteCourseCategory(@PathVariable Long id) {
        courseCategoryServices.delete(id);
        return ResponseEntity.noContent().build();
    }


}
