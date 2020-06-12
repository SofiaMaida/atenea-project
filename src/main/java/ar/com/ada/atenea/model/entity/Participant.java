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

    @Column(nullable = false, length = 100)
    private String password;

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

}
