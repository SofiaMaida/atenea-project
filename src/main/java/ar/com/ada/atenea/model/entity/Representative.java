package ar.com.ada.atenea.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "Representative")
public class Representative {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, length = 11)
    private Long numberDoc;

    @Column(nullable = false, length = 100)
    private String positionCompany;

    @Column(nullable = false, length = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "DocumentType_id", referencedColumnName = "id", nullable = false)
    private DocumentType documentType;

    @ManyToOne
    @JoinColumn(name = "Company_id", referencedColumnName = "id", nullable = false)
    private Company company;

}
