package ar.com.ada.atenea.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "Scholarship")
public class Socioeconomic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean isStudy;

    @Column(nullable = false)
    private Boolean isWorking;

    @Column(nullable = false)
    private Boolean haveIncome;

    @Column(nullable = false)
    private Integer howMuch;

    @Column(nullable = false)
    private Boolean haveDependentFamily;

   /* @ManyToOne
    @JoinColumn(name = "participants_id", nullable = true)
    private Participants participants;*/

    public Socioeconomic(Boolean isStudy, Boolean isWorking, Boolean haveIncome, Integer howMuch, Boolean haveDependentFamily) {
        this.isStudy = isStudy;
        this.isWorking = isWorking;
        this.haveIncome = haveIncome;
        this.howMuch = howMuch;
        this.haveDependentFamily = haveDependentFamily;
    }

    public Socioeconomic(Long id) {
        this.id = id;
    }
}
