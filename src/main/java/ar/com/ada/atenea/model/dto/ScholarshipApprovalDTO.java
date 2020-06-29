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
public class ScholarshipApprovalDTO implements Serializable {

    @NotNull(message = "is approved is required")
    private Boolean isApproved;

    @NotNull(message = "scholarship percentage is required")
    private Integer scholarshipPercentage;
}
