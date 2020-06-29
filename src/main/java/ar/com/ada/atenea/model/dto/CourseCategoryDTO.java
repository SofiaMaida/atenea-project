package ar.com.ada.atenea.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseCategoryDTO implements Serializable {

    private Long id;

    @NotBlank(message = "type_category is required")
    private String typeCategory;

    @NotNull(message = "courseId is required")
    private Long courseId;

    @JsonIgnoreProperties(value = "courseCategory")
    private Set<CourseDTO> courses;

}
