package ar.com.ada.atenea.model.repository;

import ar.com.ada.atenea.model.entity.SocioEconomic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("socioeconomicRepository")
public interface SocioEconomicRepository extends JpaRepository<SocioEconomic, Long> {

}

