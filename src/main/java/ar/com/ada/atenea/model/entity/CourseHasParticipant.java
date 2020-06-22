package ar.com.ada.atenea.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "Course_has_Participant")
public class CourseHasParticipant implements Serializable {

    @EmbeddedId
    private CourseParticipantId id;

    @ManyToOne
    @MapsId("courseId")
    private Course course;

    @ManyToOne
    @MapsId("participantId")
    private Participant participant;

    //cursos realizados: terminados o no
    @Column(nullable = false)
    @Type(type = "org.hibernate.type.BooleanType")
    private Boolean hasFinish;

    //tipo de solicitud del curso: compra directa (true) o beca (false)
    @Column(nullable = false)
    @Type(type = "org.hibernate.type.BooleanType")
    private Boolean isBuy;

    //estado de la solicitud: true (aceptado) / false (denegado)
    @Column(nullable = false)
    @Type(type = "org.hibernate.type.BooleanType")
    private Boolean hasApproved;

    //porcentaje de la beca
    @Column
    private Integer percentage;

}
