package ar.com.ada.atenea.model.repository;

import ar.com.ada.atenea.model.entity.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("courseCategoryRepository")
public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {

}
