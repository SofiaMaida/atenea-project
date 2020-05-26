package ar.com.ada.atenea.model.repository;

import ar.com.ada.atenea.model.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
