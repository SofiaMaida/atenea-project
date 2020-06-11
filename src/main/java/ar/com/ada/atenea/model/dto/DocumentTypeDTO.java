package ar.com.ada.atenea.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "documentType"})
public class DocumentTypeDTO implements Serializable {

    @NotNull(message = "DocumentType.id is required")
    private Long id;

    private String documentType;

}
