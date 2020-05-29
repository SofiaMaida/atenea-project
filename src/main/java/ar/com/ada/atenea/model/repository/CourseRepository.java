package ar.com.ada.atenea.model.repository;

import ar.com.ada.atenea.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("courseRepository")
public interface CourseRepository extends JpaRepository<Course, Long> {

    // Optional<Course> findByNameOrGender(String name, String gender);
    // SELECT * FROM Course WHERE name = ? AND gender = ?
    // SELECT * FROM Course WHERE name = ? OR gender = ?

}
