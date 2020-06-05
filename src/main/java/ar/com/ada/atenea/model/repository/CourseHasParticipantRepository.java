package ar.com.ada.atenea.model.repository;

import ar.com.ada.atenea.model.entity.CourseHasParticipant;
import ar.com.ada.atenea.model.entity.CourseParticipantId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("courseHasParticipantRepository")
public interface CourseHasParticipantRepository extends JpaRepository<CourseHasParticipant, CourseParticipantId> {

}
