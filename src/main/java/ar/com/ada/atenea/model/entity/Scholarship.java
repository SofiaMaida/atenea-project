package ar.com.ada.atenea.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "Scholarship")
public class Scholarship {

    private Long id;

    private Boolean isStudy;

    private Boolean isWorking;

    private Boolean haveIncome;

    private Integer howMuch;

    private Boolean haveDependentFamily;

   /* @ManyToOne
    @JoinColumn(name = "participants_id", nullable = true)
    private Participants participants;*/

    public Scholarship(Boolean isStudy, Boolean isWorking, Boolean haveIncome, Integer howMuch, Boolean haveDependentFamily) {
        this.isStudy = isStudy;
        this.isWorking = isWorking;
        this.haveIncome = haveIncome;
        this.howMuch = howMuch;
        this.haveDependentFamily = haveDependentFamily;
    }

    public Scholarship(Long id) {
        this.id = id;
    }
}
