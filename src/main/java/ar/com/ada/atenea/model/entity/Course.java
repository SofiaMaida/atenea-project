package ar.com.ada.atenea.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@Entity(name = "Course")
public class Course {

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

    @ManyToMany
    @JoinTable(name = "Course_has_Company",
            joinColumns = @JoinColumn(name = "Course_id"),
            inverseJoinColumns = @JoinColumn(name = "Company_id"))
    private Set<Company> companies;

    @ManyToMany
    @JoinTable(name = "Course_has_Participants",
            joinColumns = @JoinColumn(name = "Course_id"),
            inverseJoinColumns = @JoinColumn(name = "Participants_id"))
    private Set<Participants> participants;

    @OneToMany(mappedBy = "course")
    private List<CourseCategory> courseCategories;

    @OneToMany(mappedBy = "course")
    private List<Waiting> waitings;

    public Course(Long id) {
        this.id = id;
    }

    public Course(String name, String description, String modality, Integer price, Integer hours, String category, Integer amountParticipants, Integer amountScholarship) {
        this.name = name;
        this.description = description;
        this.modality = modality;
        this.price = price;
        this.hours = hours;
        this.category = category;
        this.amountParticipants = amountParticipants;
        this.amountScholarship = amountScholarship;
    }
}
