package ar.com.ada.atenea.model.repository;

import ar.com.ada.atenea.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("companyRepository")
public interface CompanyRepository extends JpaRepository<Company, Long> {

    // Optional<Company> findByNameOrCategory(String name, String gender);
    // SELECT * FROM Company WHERE name = ? AND category = ?
    // SELECT * FROM Company WHERE name = ? OR category = ?

}
