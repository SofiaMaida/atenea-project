package ar.com.ada.atenea.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "gender", "birthday"})
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

    private Integer scholarshipCounter;

    private Integer participantsCounter;

    //@JsonIgnoreProperties(value = "courses")
    //private CourseCategoryDTO courseCategory;

}
