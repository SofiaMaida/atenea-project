package ar.com.ada.atenea.model.dto;

import ar.com.ada.atenea.model.entity.Course;
import ar.com.ada.atenea.model.entity.CourseParticipantId;
import ar.com.ada.atenea.model.entity.Participant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
public class CourseHasParticipantDTO implements Serializable {

    private CourseParticipantId id;

    private Course course;

    private Participant participant;

    //cursos realizados: terminados o no
    @NotBlank(message = "has_finish is required")
    private Boolean hasFinish;

    //tipo de solicitud del curso: compra directa (true) o beca (false)
    @NotBlank(message = "type_request is required")
    private Boolean typeRequest;

    //estado de la solicitud: true (aceptado) / false (denegado)
    @NotBlank(message = "status is required")
    private Boolean status;

    //porcentaje de la beca
    @NotBlank(message = "percentage is required")
    private Integer percentage;
}
