package ar.com.ada.atenea.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "Gender")
public class Gender {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String typeGender;

    @OneToMany(mappedBy = "gender")
    private List<Participant> participants;

    public Gender(String typeGender) {
        this.typeGender = typeGender;
    }
}
