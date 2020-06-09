package ar.com.ada.atenea.controller;

import ar.com.ada.atenea.model.dto.GenderDTO;
import ar.com.ada.atenea.service.GenderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/genders")
public class GenderController {

    @Autowired @Qualifier("genderServices")
    private GenderServices genderServices;

    @GetMapping({"", "/"})
    public ResponseEntity getAllGenders() {
        List<GenderDTO> all = genderServices.findAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping({"", "/"})
    public ResponseEntity addNewGender(@Valid @RequestBody GenderDTO genderDTO) throws URISyntaxException {
        GenderDTO save = genderServices.save(genderDTO);
        return ResponseEntity.created(new URI("/gender/" + genderDTO.getId())).body(save);
    }

}
