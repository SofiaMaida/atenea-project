package ar.com.ada.atenea.controller;

import ar.com.ada.atenea.model.dto.CourseCategoryDTO;
import ar.com.ada.atenea.service.CourseCategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/courseCategories")
public class CourseCategoryController {

    @Autowired @Qualifier("courseCategoryServices")
    private CourseCategoryServices courseCategoryServices;


    @GetMapping({"", "/"})
    public ResponseEntity getAllCourseCategory() {
        List<CourseCategoryDTO> all = courseCategoryServices.findAll();
        return ResponseEntity.ok(all);
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
