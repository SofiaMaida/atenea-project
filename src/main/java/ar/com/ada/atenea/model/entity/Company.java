package ar.com.ada.atenea.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "Company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private Long cuil;

    @Column(nullable = false, length = 100)
    private String typeCompany;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false, length = 100)
    private Integer yearFoundation;

    @Column(nullable = false, length = 50)
    private Integer phone;

    @Column(nullable = false, length = 100)
    private String typeCategory;

    @OneToMany(mappedBy = "company")
    private List<Course> courses;

    @OneToMany(mappedBy = "company")
    private List<Representative> representatives;

}
