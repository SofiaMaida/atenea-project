package ar.com.ada.atenea.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity(name = "Genre")
public class Genre {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String typeGenre;

    @OneToMany(mappedBy = "genre")
    private List<Participants> participants;

    public Genre(Long id) {
        this.id = id;
    }

    public Genre(String typeGenre, List<Participants> participants) {
        this.typeGenre = typeGenre;
        this.participants = participants;
    }
}
