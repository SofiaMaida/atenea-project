package ar.com.ada.atenea.model.repository;

import ar.com.ada.atenea.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("genreRepository")
public interface GenreRepository extends JpaRepository<Genre, Long> {

}
