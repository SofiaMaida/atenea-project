package ar.com.ada.atenea.model.repository;

import ar.com.ada.atenea.model.entity.Participants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("participantsRepository")
public interface ParticipantsRepository extends JpaRepository<Participants, Long> {

}
