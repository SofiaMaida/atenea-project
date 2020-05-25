package ar.com.ada.atenea.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity(name = "Company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private Integer cuil;

    @Column(nullable = false, length = 100)
    private String typeCompany;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false, length = 100)
    private Integer year;

    @Column(nullable = false, length = 100)
    private Integer phone;

    @Column(nullable = false, length = 100)
    private String typeCategory;

    @OneToMany(mappedBy = "company")
    private List<Representative> representatives;

    @ManyToMany(mappedBy = "companies")
    private List<Course> courses;

    public Company(String name, Integer cuil, String typeCompany, String address, Integer year, Integer phone) {
        this.name = name;
        this.cuil = cuil;
        this.typeCompany = typeCompany;
        this.address = address;
        this.year = year;
        this.phone = phone;
    }

    public Company(Long id) {
        this.id = id;
    }

    //public void addCompany(Company company) {
      //  this.companys.add(company);}




}
