package ar.com.ada.atenea.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "name", "cuil", "typeCompany", "address", "yearFoundation", "phone"})
public class CompanyDTO implements Serializable {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "cuil is required")
    @Pattern(regexp = "[0-9]")
    private Long cuil;

    @NotBlank(message = "address is required")
    private String address;

    @NotNull(message = "foundation year is required")
    @PastOrPresent(message = "foundation year must be past or present")
    @JsonFormat(pattern = "yyyy")
    private Integer yearFoundation;

    @NotBlank(message = "phone is required")
    @Pattern(regexp = "[0-9]")
    private Integer phone;

   @JsonIgnoreProperties(value = "companies")
    private Set<RepresentativeDTO> representatives;

    //@JsonIgnoreProperties(value = "companies")
    //private CompanyCategoryDTO companyCategory;

}

/*
{
    "name":"",
    "cuil":20304005006,
    "typeCompany":"",
    "address":"",
    "yearFoundation":2009,
    "phone":1212341234,
    "companyCategory":""
}
 */
