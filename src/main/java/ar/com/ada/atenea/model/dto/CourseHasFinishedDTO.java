package ar.com.ada.atenea.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseHasFinishedDTO implements Serializable {

    @NotNull(message = "course has finished is required")
    private Boolean courseHasFinished;

    /*

    {
        "CourseHasFinished": false,
        "isScholarship": true,
        "isApproved": true,
        "scholarshipPercentage": 75
    }

    */

}
