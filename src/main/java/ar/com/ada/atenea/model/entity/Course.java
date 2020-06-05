package ar.com.ada.atenea.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@Entity(name = "Course")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String description;

    @Column(nullable = false, length = 100)
    private String modality;

    @Column(nullable = false, length = 100)
    private Integer price;

    @Column(nullable = false, length = 100)
    private Integer hours;

    @Column(nullable = false, length = 100)
    private String category;

    @Column(nullable = false, length = 100)
    private Integer amountParticipants;

    @Column(nullable = false, length = 100)
    private Integer amountScholarship;

    @Column(nullable = true)
    private Integer scholarshipCounter;

    @Column(nullable = true)
    private Integer participantsCounter;

    @OneToMany(mappedBy = "course")
    private List<CourseCategory> courseCategories;

    @ManyToOne
    @JoinColumn(name = "Company_id", referencedColumnName = "id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "course")
    private Set<CourseHasParticipant> courseHasParticipants;

}
