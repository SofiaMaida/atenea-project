package ar.com.ada.atenea.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "DocumentType")
public class DocumentType {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String documentType;

    //@OneToMany(mappedBy = "documentType")
    //private List<Representative> representatives;

    //@OneToMany(mappedBy = "documentType")
    //private List<Participant> participants;

    //@OneToMany(mappedBy = "documentType")
    //private List<Administrator> administrators;


    public DocumentType(String documentType) {
        this.documentType = documentType;
    }
}
