package ar.com.ada.atenea.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "CourseCategory")
public class CourseCategory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, length = 100)
    private String typeCategory;

    /*@ManyToOne
    @JoinColumn(name = "course_id", nullable = true)
    private Course course;*/

    public CourseCategory(Long id) {
        this.id = id;
    }


}
