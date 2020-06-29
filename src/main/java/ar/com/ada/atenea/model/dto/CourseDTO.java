package ar.com.ada.atenea.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "name", "description", "modality", "price", "hours", "amountParticipants", "amountScholarship"})
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

    public CourseDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public CourseDTO setName(String name) {
        this.name = name;
        return this;
    }

    public CourseDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public CourseDTO setModality(String modality) {
        this.modality = modality;
        return this;
    }

    public CourseDTO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public CourseDTO setHours(Integer hours) {
        this.hours = hours;
        return this;
    }

    public CourseDTO setAmountParticipants(Integer amountParticipants) {
        this.amountParticipants = amountParticipants;
        return this;
    }

    public CourseDTO setAmountScholarship(Integer amountScholarship) {
        this.amountScholarship = amountScholarship;
        return this;
    }
}
/*

{
    "name":"backend",
    "description":"curso de desarrollo backend",
    "modality":"presencial",
    "price": 20000,
    "hours":400,
    "amountParticipants":20,
    "amountScholarship": 5,
    "companyId": 1
    }
*/