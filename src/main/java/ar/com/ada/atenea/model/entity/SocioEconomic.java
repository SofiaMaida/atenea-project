package ar.com.ada.atenea.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "SocioEconomic")
public class SocioEconomic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.BooleanType")
    private Boolean isStudy;

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.BooleanType")
    private Boolean hasJob;

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.BooleanType")
    private Boolean haveIncome;

    @Column(nullable = false)
    private Integer howMuch;

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.BooleanType")
    private Boolean familyInCharge;

    @Column(nullable = false)
    private Integer howMuchFamily;

    @OneToOne
    @JoinColumn(name = "Participant_id", referencedColumnName = "id", nullable = false)
    private Participant participant;


}
