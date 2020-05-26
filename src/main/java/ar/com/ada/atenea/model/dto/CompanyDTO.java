package ar.com.ada.atenea.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "cuil", "type_company", "address", "type_category", "year", "phone"})
public class CompanyDTO implements Serializable {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "cuil is required")
    @Pattern(regexp = "[0-9]")
    private Integer cuil;

    @NotBlank(message = "type company is required")
    private String typeCompany;

    @NotBlank(message = "address is required")
    private String address;

    @NotNull(message = "foundation year is required")
    @PastOrPresent(message = "foundation year must be past or present")
    @JsonFormat(pattern = "yyyy")
    private Integer year;

    @NotBlank(message = "phone is required")
    @Pattern(regexp = "[0-9]")
    private Integer phone;

    @NotBlank(message = "type category is required")
    private String typeCategory;

}
