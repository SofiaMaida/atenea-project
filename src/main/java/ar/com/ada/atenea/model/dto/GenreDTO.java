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
@JsonPropertyOrder({"id", "type_genre"})
public class GenreDTO implements Serializable {

    private Long id;

    @NotBlank(message = "type_genre is required")
    private String typeGenre;

    @JsonIgnoreProperties(value = "genre")
    private Set<ParticipantsDTO> participants;

    public GenreDTO(Long id, String typeGenre) {
        this.id = id;
        this.typeGenre = typeGenre;
    }

    public GenreDTO(String typeGenre) {
        this.typeGenre = typeGenre;
    }
}
