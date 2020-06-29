package ar.com.ada.atenea.controller;

import ar.com.ada.atenea.model.dto.CourseDTO;
import ar.com.ada.atenea.service.SearchServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired @Qualifier("searchServices")
    private SearchServices searchServices;

    // ?page=0&categoryId=1
    //localhost:8080/search/courses/category/1?page=0
    @GetMapping({"/courses/category/{courseCategoryId}", "/courses/category/{courseCategoryId}/"})
    public ResponseEntity getAllCoursesByCategory(@RequestParam Optional<Integer> categoryId,
                                                  @RequestParam Optional<Integer> page) {

        List<CourseDTO> all = searchServices.findAllCourseByCategory(categoryId.orElse(null), page.orElse(0));
        return ResponseEntity.ok(all);
    }

    //localhost:8080/search/courses/company/1?page=0
    @GetMapping({"/courses/company/{companyId}", "/courses/company/{companyId}/"})
    public ResponseEntity getAllCoursesByCompany(@PathVariable Long companyId,
                                                 @RequestParam Optional<Integer> page) {

        List<CourseDTO> allCoursesByCompany = searchServices.getAllCourseByCompany(companyId, page.orElse(0));
        return ResponseEntity.ok(allCoursesByCompany);
    }

    //localhost:8080/search/courses/available/1?page=0
    @GetMapping({"/courses/available", "/courses/available/"})
    public ResponseEntity getAllCourseAvailable(@RequestParam Optional<Integer> page) {

        List<CourseDTO> allCourseAvailable = searchServices.getAllCourseAvailable(page.orElse(0));
        return ResponseEntity.ok(allCourseAvailable);
    }

    //localhost:8080/search/courses/company/1/category/1?page=0
    @GetMapping({"/courses/company/{companyId}/category/{courseCategoryId}", "/courses/company/{companyId}/category/{courseCategoryId}/"})
    public ResponseEntity getAllCourseByCompanyAndCategory(@RequestParam Optional<Integer> page,
                                                           @PathVariable Long companyId, @PathVariable Long courseCategoryId) {

        List<CourseDTO> allCourseByCompanyAndCategory = searchServices.getAllCourseByCompanyAndCategory(companyId, courseCategoryId, page.orElse(0));
        return ResponseEntity.ok(allCourseByCompanyAndCategory);
    }

    //localhost:8080/search/courses/participants/in-progress/true?page=0
    @GetMapping({"/courses/participants/in-progress/{hasFinish}", "/courses/participants/in-progress/{hasFinish}/"})
    public ResponseEntity getAllCourseByParticipantsStatus(@RequestParam Optional<Integer> page, @PathVariable String hasFinish) {

        List<CourseDTO> allCourseByParticipantsStatus = searchServices.getAllCourseProgressStatus(hasFinish, page.orElse(0));
        return ResponseEntity.ok(allCourseByParticipantsStatus);
    }

}
