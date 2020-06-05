package ar.com.ada.atenea.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "last_name", "number_doc", "position_company", "email"})
public class RepresentativeDTO implements Serializable {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "last_name is required")
    private String lastName;

    @NotBlank(message = "number_doc is required")
    @Pattern(regexp = "[0-9]")
    private Integer numberDoc;

    @NotBlank(message = "name is required")
    private String positionCompany;

    @Email
    @NotBlank(message = "email is required")
    private String email;

    @Size(message = "password must be least 4 characters long", min = 4)
    @NotBlank(message = "password is required")
    private String password;

    /*@JsonIgnoreProperties(value = "representatives")
    private CompanyDTO companies;

    @JsonIgnoreProperties(value = "representatives")
    private DocumentTypeDTO documentType;*/

    public RepresentativeDTO(Long id, String name, String lastName,Integer numberDoc, String positionCompany, String email, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.numberDoc = numberDoc;
        this.positionCompany = positionCompany;
        this.email = email;
        this.password = password;
    }

    public RepresentativeDTO(String name, String lastName,Integer numberDoc, String positionCompany, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.numberDoc = numberDoc;
        this.positionCompany = positionCompany;
        this.email = email;
        this.password = password;
    }
}
