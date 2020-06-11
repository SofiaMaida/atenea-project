package ar.com.ada.atenea.controller;

import ar.com.ada.atenea.model.dto.SocioEconomicDTO;
import ar.com.ada.atenea.service.SocioEconomicServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/socioeconomics")
public class SocioEconomicController {

    @Autowired @Qualifier("socioEconomicServices")
    private SocioEconomicServices socioEconomicServices;

    @GetMapping({"", "/"}) // localhost:8080/socioeconomics && localhost:8080/socioeconomics/ [GET]
    public ResponseEntity getAllSocioEconomics() {
        List<SocioEconomicDTO> all = socioEconomicServices.findAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping({"", "/"}) // localhost:8080/socioeconomics && localhost:8080/socioeconomics/ [POST]
    public ResponseEntity addNewSocioEconomic(@Valid @RequestBody SocioEconomicDTO socioeconomicDTO) throws URISyntaxException {
        SocioEconomicDTO socioEconomicSaved = socioEconomicServices.save(socioeconomicDTO);
        return ResponseEntity.created(new URI("/socioeconomics/" + socioeconomicDTO.getId())).body(socioEconomicSaved);
    }

}
