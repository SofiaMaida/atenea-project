package ar.com.ada.atenea.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity(name = "Participants")
public class Participants {

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


    /*@ManyToOne
    @JoinColumn(name = "documentType_id", nullable = true)
    private DocumentType docType;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = true)
    private Genre genre;

    @OneToOne(mappedBy = "participants")
    private List<Scholarship> scholarships;

    @ManyToMany(mappedBy = "participants")
    private List<Course> courses;*/

    public Participants(Long id) {
        this.id = id;
    }

}
