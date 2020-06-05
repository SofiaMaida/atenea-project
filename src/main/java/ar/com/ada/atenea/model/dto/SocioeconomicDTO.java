package ar.com.ada.atenea.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
public class SocioeconomicDTO implements Serializable {

    private Long id;

    @NotBlank(message = "this data is required")
    private Boolean isStudy;

    @NotBlank(message = "this data is required")
    private Boolean isWorking;

    @NotBlank(message = "this data is required")
    private Boolean haveIncome;

    @NotBlank(message = "this data is required")
    private Boolean howMuch;

    @NotBlank(message = "this data is required")
    private Boolean haveDependentFamily;

    //@JsonIgnoreProperties(value = "socioeconomic") //participants_id
    //private ParticipantsDTO participants;
}
