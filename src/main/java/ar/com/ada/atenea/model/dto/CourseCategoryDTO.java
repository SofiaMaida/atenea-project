package ar.com.ada.atenea.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
public class CourseCategoryDTO implements Serializable {

    private Long id;

    @NotBlank(message = "type_category is required")
    private String typeCategory;

    @JsonIgnoreProperties(value = "courseCategory")
    private Set<CourseDTO> courses;

}
