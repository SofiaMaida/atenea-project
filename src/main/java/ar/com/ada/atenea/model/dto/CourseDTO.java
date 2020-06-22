package ar.com.ada.atenea.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "description", "modality", "price", "hours", "category", "amountParticipants", "amountScholarship"})
public class CourseDTO implements Serializable {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "description is required")
    private String description;

    @NotBlank(message = "modality is required")
    private String modality;

    @NotBlank(message = "price is required")
    private Integer price;

    @NotBlank(message = "hours is required")
    private Integer hours;

    @NotBlank(message = "category is required")
    private String category;

    @NotBlank(message = "amount_participants is required")
    private Integer amountParticipants;

    @NotBlank(message = "amount_scholarship is required")
    private Integer amountScholarship;

    @NotNull(message = "companyId is required")
    private Long companyId;

    private Integer scholarshipCounter;

    private Integer participantsCounter;

    @JsonIgnoreProperties(value = "courses")
    private CourseCategoryDTO courseCategory;

}
/*

{
    "name":"backend",
    "description":"curso de desarrollo backend",
    "modality":"presencial",
    "price": 20000,
    "hours":400,
    "category":"IT",
    "amountParticipants":20,
    "amountScholarship": 5,
    "companyId": 1
    }
*/