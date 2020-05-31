package ar.com.ada.atenea.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity(name = "Representative")
public class Representative {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, length = 11)
    private Integer numberDoc;

    @Column(nullable = false, length = 100)
    private String positionCompany;

    @Column(nullable = false, length = 100)
    private String email;


    @Column(nullable = false, length = 100)
    private String password;

    /*@ManyToOne
    @JoinColumn(name = "documentType_id", nullable = true)
    private DocumentType docType;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = true)
    private Company company;*/

    public Representative(Long id) {
        this.id = id;
    }

    public Representative(String name, String lastName, Integer numberDoc, String positionCompany, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.numberDoc = numberDoc;
        this.positionCompany = positionCompany;
        this.email = email;
        this.password = password;
    }
}
