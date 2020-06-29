package ar.com.ada.atenea.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Course")
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
    private Integer amountParticipants;

    @Column(nullable = false, length = 100)
    private Integer amountScholarship;

    @Column(nullable = true)
    private Integer scholarshipCounter;

    @Column(nullable = true)
    private Integer participantsCounter;

    @ManyToOne
    @JoinColumn(name = "Company_id", referencedColumnName = "id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "CourseCategory_id", referencedColumnName = "id", nullable = false)
    private CourseCategory courseCategory;

    @OneToMany(mappedBy = "course")
    private Set<CourseHasParticipant> courseHasParticipants;


    public Course setId(Long id) {
        this.id = id;
        return this;
    }

    public Course setName(String name) {
        this.name = name;
        return this;
    }

    public Course setDescription(String description) {
        this.description = description;
        return this;
    }

    public Course setModality(String modality) {
        this.modality = modality;
        return this;
    }

    public Course setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Course setHours(Integer hours) {
        this.hours = hours;
        return this;
    }

    public Course setAmountParticipants(Integer amountParticipants) {
        this.amountParticipants = amountParticipants;
        return this;
    }

    public Course setAmountScholarship(Integer amountScholarship) {
        this.amountScholarship = amountScholarship;
        return this;
    }

    }
