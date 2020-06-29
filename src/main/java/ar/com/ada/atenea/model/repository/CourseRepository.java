package ar.com.ada.atenea.model.repository;

import ar.com.ada.atenea.model.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("courseRepository")
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value = "SELECT * FROM Course WHERE scholarshipCounter > 0 AND participantCounter > 0", nativeQuery = true)
    Page<Course> findAllCourseAvailable(Pageable pageable);

    @Query(value = "SELECT * FROM Course INNER JOIN CourseCategory WHERE CourseCategory_id = :id", nativeQuery = true)
    Page<Course> findAllCourseByCategory(@Param("id") Integer id, Pageable pageable);

    @Query(value = "SELECT * FROM Course c WHERE Company_id = :companyId", nativeQuery = true)
    Page<Course> findAllByCompany(@Param("companyId") Long companyId, Pageable pageable);

    @Query(value = "SELECT * FROM Course c WHERE c.Company_id = :companyId AND c.CourseCategory_id = :courseCategoryId", nativeQuery = true)
    Page<Course> findAllByCompanyAndCategory(@Param("companyId") Long companyId, @Param("courseCategoryId") Long courseCategoryId, Pageable pageable);

    @Query(value = "SELECT c.* FROM Course c INNER JOIN CourseHasParticipant cp ON c.id = cp.course_id WHERE cp.hasFinish = :hasFinish GROUP BY c.id",
            countQuery = "SELECT DISTINCT COUNT(*) FROM Course c LEFT JOIN CourseHasParticipant cp ON c.id = cp.course_id WHERE cp.hasFinish = :hasFinish GROUP BY c.id",
            nativeQuery = true)
    Page<Course> findAllByCourseByParticipantsProgressStatus(@Param("hasFinish") Boolean hasFinish, Pageable pageable);


}
