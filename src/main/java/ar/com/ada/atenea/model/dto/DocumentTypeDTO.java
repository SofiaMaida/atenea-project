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
@JsonPropertyOrder({"id", "type_doc"})
public class DocumentTypeDTO implements Serializable {

    private Long id;

    @NotBlank(message = "type_doc is required")
    private String typeDoc;

    @JsonIgnoreProperties(value = "documentType")
    private Set<RepresentativeDTO> representatives;

    @JsonIgnoreProperties(value = "documentType")
    private Set<ParticipantsDTO> participants;

    @JsonIgnoreProperties(value = "documentType")
    private Set<AdministratorDTO> administrators;

    public DocumentTypeDTO(Long id, String typeDoc) {
        this.id = id;
        this.typeDoc = typeDoc;
    }

    public DocumentTypeDTO(String typeDoc) {
        this.typeDoc = typeDoc;
    }
}
