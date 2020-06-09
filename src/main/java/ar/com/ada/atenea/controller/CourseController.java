package ar.com.ada.atenea.controller;

import ar.com.ada.atenea.model.dto.CourseDTO;
import ar.com.ada.atenea.service.CourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired @Qualifier("courseServices")
    private CourseServices courseServices;

    @GetMapping({"", "/"})
    public ResponseEntity getAllCourses() {
        List<CourseDTO> all = courseServices.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity getCourseById(@PathVariable Long id) {
        CourseDTO courseById = courseServices.findCourseById(id);
        return ResponseEntity.ok(courseById);
    }

    @PostMapping({"", "/"})
    public ResponseEntity addNewCourse(@Valid @RequestBody CourseDTO courseDTO) throws URISyntaxException {
        CourseDTO courseSaved = courseServices.save(courseDTO);
        return ResponseEntity.created(new URI("/courses/" + courseDTO.getId()))
                .body(courseSaved);
    }

    @PutMapping({"/{id}", "/{id}/"})
    public ResponseEntity updateCourseById(@Valid @RequestBody CourseDTO courseDTO, @PathVariable Long id) {
        CourseDTO courseUpdated = courseServices.updateCourse(courseDTO, id);
        return ResponseEntity.ok(courseUpdated);
    }

    @DeleteMapping({"", "/"})
    public ResponseEntity deleteCourse(@PathVariable Long id) {
        courseServices.delete(id);
        return ResponseEntity.noContent().build();
    }

}
