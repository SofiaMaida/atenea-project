package ar.com.ada.atenea.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity(name = "DocumentType")
public class DocumentType {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String docType;

    @OneToMany(mappedBy = "documentType")
    private List<Representative> representatives;

    @OneToMany(mappedBy = "documentType")
    private List<Participants> participants;

    @OneToMany(mappedBy = "documentType")
    private List<Administrator> administrators;

    public DocumentType(String docType) {
        this.docType = docType;
    }

    public DocumentType(Long id) {
        this.id = id;
    }
}
