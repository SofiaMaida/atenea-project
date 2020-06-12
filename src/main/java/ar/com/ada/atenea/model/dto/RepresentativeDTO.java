package ar.com.ada.atenea.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "lastName", "numberDoc", "positionCompany", "email"})
public class RepresentativeDTO implements Serializable {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "lastName is required")
    private String lastName;

    @NotBlank(message = "numberDoc is required")
    @Pattern(regexp = "[0-9]")
    private Long numberDoc;

    @NotBlank(message = "positionCompany is required")
    private String positionCompany;

    @Email
    @NotBlank(message = "email is required")
    private String email;

    @Size(message = "password must be least 4 characters long", min = 4)
    @NotBlank(message = "password is required")
    private String password;

    @NotNull(message = "companyId is required")
    private Long companyId;

    @JsonIgnoreProperties(value = "representatives")
    @Valid
    @NotNull(message = "DocumentType is required")
    private DocumentTypeDTO documentType;

    @JsonIgnoreProperties(value = "representatives")
    private CompanyDTO companies;

}

/*

{
    "name":"",
    "lastName":"",
    "numberDoc":"",
    "positionCompany":"",
    "email":"",
    "password":"",
    "companyId":1,
    "documentType": {
        "id": 1
    }
}
 */