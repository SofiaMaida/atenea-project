package ar.com.ada.atenea.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "name", "last_name", "birthday", "location", "number_doc"})
public class ParticipantsDTO implements Serializable {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "lastName is required")
    private String lastName;

    @NotNull(message = "birthday is required")
    @Past(message = "the birthday must be past date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotBlank(message = "location is required")
    private String location;

    @NotBlank(message = "numberDoc is required")
    @Pattern(regexp = "[0-9]")
    private Integer numberDoc;

    @NotNull(message = "genderId is required")
    private Long genderId;

    @JsonIgnoreProperties(value = "participants")
    private GenderDTO gender;

    @JsonIgnoreProperties(value = "participants")
    @Valid
    @NotNull(message = "DocumentType is required")
    private DocumentTypeDTO documentType;

    @JsonIgnoreProperties(value = "participants")
    private Set<SocioEconomicDTO> socioeconomic;

    public ParticipantsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public ParticipantsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public ParticipantsDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ParticipantsDTO setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public ParticipantsDTO setLocation(String location) {
        this.location = location;
        return this;
    }

    public ParticipantsDTO setNumberDoc(Integer numberDoc) {
        this.numberDoc = numberDoc;
        return this;
    }



}
