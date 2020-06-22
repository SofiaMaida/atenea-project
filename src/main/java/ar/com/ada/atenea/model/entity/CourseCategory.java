package ar.com.ada.atenea.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "CourseCategory")
public class CourseCategory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String typeCategory;

    @OneToMany(mappedBy = "courseCategory")
    private List<Course> courses;

    public CourseCategory setId(Long id) {
        this.id = id;
        return this;
    }

    public CourseCategory setTypeCategory(String typeCategory) {
        this.typeCategory = typeCategory;
        return this;
    }

}
