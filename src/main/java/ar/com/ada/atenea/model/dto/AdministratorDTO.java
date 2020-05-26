package ar.com.ada.atenea.model.dto;

import ar.com.ada.atenea.model.entity.DocumentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
public class AdministratorDTO implements Serializable {

    private Long id;

    private Boolean enabled;

    private String name;

    private String lastName;

    private String password;

    private String email;

    private DocumentType documentType;


}
