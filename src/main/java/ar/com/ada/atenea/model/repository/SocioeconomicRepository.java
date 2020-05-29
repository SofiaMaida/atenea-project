package ar.com.ada.atenea.model.repository;

import ar.com.ada.atenea.model.entity.Socioeconomic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("socioeconomicRepository")
public interface SocioeconomicRepository extends JpaRepository<Socioeconomic, Long> {
}
