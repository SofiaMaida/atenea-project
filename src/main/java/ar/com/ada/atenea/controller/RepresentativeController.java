package ar.com.ada.atenea.controller;

import ar.com.ada.atenea.model.dto.RepresentativeDTO;
import ar.com.ada.atenea.model.entity.Representative;
import ar.com.ada.atenea.service.RepresentativeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/representatives")
public class RepresentativeController {

    @Autowired @Qualifier("representativeServices")
    private RepresentativeServices representativeServices;

    @GetMapping({"", "/"})
    public ResponseEntity getAllRepresentatives() {
        List<RepresentativeDTO> all = representativeServices.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity getRepresentativeById(@PathVariable Long id) {
        RepresentativeDTO representativeById = representativeServices.findRepresentativeById(id);
        return ResponseEntity.ok(representativeById);
    }

    @PostMapping({"", "/"})
    public ResponseEntity addNewRepresentative(@Valid @RequestBody RepresentativeDTO representativeDTO) throws URISyntaxException {
        RepresentativeDTO representativeSaved = representativeServices.save(representativeDTO);
        return ResponseEntity.created(new URI("/representative/" + representativeDTO.getId())).body(representativeSaved);
    }

    @PutMapping({ "/{id}", "/{id}/" })
    public ResponseEntity updateRepresentativeById(@Valid @RequestBody RepresentativeDTO representativeDTO, @PathVariable Long id) {
        RepresentativeDTO representativeUpdated = representativeServices.updateRepresentative(representativeDTO, id);
        return ResponseEntity.ok(representativeUpdated);
    }

    @DeleteMapping({"", "/"})
    public ResponseEntity deleteRepresentative(@PathVariable Long id) {
        representativeServices.delete(id);
        return ResponseEntity.noContent().build();
    }

}
