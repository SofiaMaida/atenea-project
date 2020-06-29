package ar.com.ada.atenea.controller;

import ar.com.ada.atenea.model.dto.RepresentativeDTO;
import ar.com.ada.atenea.model.entity.Representative;
import ar.com.ada.atenea.service.RepresentativeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping({"", "/"})
    public ResponseEntity addNewRepresentative(@Valid @RequestBody RepresentativeDTO representativeDTO) throws URISyntaxException {
        RepresentativeDTO representativeSaved = representativeServices.save(representativeDTO);
        return ResponseEntity.created(new URI("/representative/" + representativeDTO.getId())).body(representativeSaved);
    }

}
