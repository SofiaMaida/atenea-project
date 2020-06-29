package ar.com.ada.atenea.model.repository;

import ar.com.ada.atenea.model.entity.CourseCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("courseCategoryRepository")
public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {

    @Query("select s from CourseCategory s where typeCategory like %:category%")
    Page<CourseCategory> findByCategory(@Param("category") String category, Pageable pageable);

}
