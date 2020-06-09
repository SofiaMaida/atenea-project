package ar.com.ada.atenea.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "last_name", "birthday", "location", "number_doc", "password"})
public class ParticipantsDTO implements Serializable {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "last_name is required")
    private String lastName;

    @NotNull(message = "birthday is required")
    @Past(message = "the birthday must be past date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotBlank(message = "location is required")
    private String location;

    @NotBlank(message = "number_doc is required")
    @Pattern(regexp = "[0-9]")
    private Integer numberDoc;

    @Size(message = "password must be least 4 characters long", min = 4)
    @NotBlank(message = "password is required")
    private String password;

    @JsonIgnoreProperties(value = "participants")
    private GenderDTO genre;

    @JsonIgnoreProperties(value = "participants")
    private DocumentTypeDTO documentType;

    @JsonIgnoreProperties(value = "participants")
    private Set<SocioeconomicDTO> socioeconomic;

    public ParticipantsDTO(Long id, String name, String lastName, Date birthday, String location, Integer numberDoc, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.location = location;
        this.numberDoc = numberDoc;
        this.password = password;
    }

    public ParticipantsDTO(String name, String lastName, Date birthday, String location, Integer numberDoc, String password) {
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.location = location;
        this.numberDoc = numberDoc;
        this.password = password;
    }
}
