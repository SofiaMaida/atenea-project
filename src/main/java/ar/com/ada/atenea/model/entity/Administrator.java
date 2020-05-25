package ar.com.ada.atenea.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "Administration")
public class Administrator {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "documentType_id", nullable = true)
    private DocumentType documentType;

    public Administrator(Long id) {
        this.id = id;
    }

    public Administrator(Boolean enabled, String name, String lastName, String password, String email, DocumentType documentType) {
        this.enabled = enabled;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.documentType = documentType;
    }
}
