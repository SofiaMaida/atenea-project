package ar.com.ada.atenea.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "typeGender"})
public class GenderDTO implements Serializable {

    private Long id;

    @NotBlank(message = "type_genre is required")
    private String typeGender;

    @JsonIgnoreProperties(value = "gender")
    private Set<ParticipantsDTO> participants;

}
