package ar.com.ada.atenea.model.dto;

import ar.com.ada.atenea.model.entity.DocumentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "lastName", "email"})
public class AdministratorDTO implements Serializable {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "lastName is required")
    private String lastName;

    @Size(message = "password must be least 4 characters long", min = 4)
    @NotBlank(message = "password is required")
    private String password;

    @Email
    @NotBlank(message = "email is required")
    private String email;

    @JsonIgnoreProperties(value = "administrator")
    @Valid
    @NotNull(message = "DocumentType is required")
    private DocumentType documentType;


}
