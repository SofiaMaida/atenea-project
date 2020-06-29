package ar.com.ada.atenea.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "Participant")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private Date birthday;

    @Column(nullable = false, length = 100)
    private String location;

    @Column(nullable = false, length = 12)
    private Integer numberDoc;

    @ManyToOne
    @JoinColumn(name = "DocumentType_id", referencedColumnName = "id", nullable = false)
    private DocumentType documentType;

    @ManyToOne
    @JoinColumn(name = "Gender_id", referencedColumnName = "id", nullable = false)
    private Gender gender;

    @OneToOne(mappedBy = "participant")
    private SocioEconomic socioEconomics;

    @OneToMany(mappedBy = "participant")
    private Set<CourseHasParticipant> participant;

    public Participant setId(Long id) {
        this.id = id;
        return this;
    }

    public Participant setName(String name) {
        this.name = name;
        return this;
    }

    public Participant setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Participant setLocation(String location) {
        this.location = location;
        return this;
    }

    public Participant setNumberDoc(Integer numberDoc) {
        this.numberDoc = numberDoc;
        return this;
    }
}
