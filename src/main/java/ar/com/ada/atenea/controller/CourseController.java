package ar.com.ada.atenea.controller;

import ar.com.ada.atenea.model.dto.CourseDTO;
import ar.com.ada.atenea.service.CourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired @Qualifier("courseServices")
    private CourseServices courseServices;

    @PreAuthorize("hasRole('REPRESENTATIVE')")
    @PostMapping({"", "/"})
    public ResponseEntity addNewCourse(@Valid @RequestBody CourseDTO courseDTO) throws URISyntaxException {
        CourseDTO courseSaved = courseServices.save(courseDTO);
        return ResponseEntity.created(new URI("/courses/" + courseDTO.getId()))
                .body(courseSaved);
    }

    @PreAuthorize("hasRole('REPRESENTATIVE')")
    @DeleteMapping({"", "/"})
    public ResponseEntity deleteCourse(@PathVariable Long id) {
        courseServices.delete(id);
        return ResponseEntity.noContent().build();
    }

}
