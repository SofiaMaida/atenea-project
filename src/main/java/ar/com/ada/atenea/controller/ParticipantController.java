package ar.com.ada.atenea.controller;

import ar.com.ada.atenea.model.dto.ParticipantsDTO;
import ar.com.ada.atenea.model.dto.SocioEconomicDTO;
import ar.com.ada.atenea.service.ParticipantServices;
import ar.com.ada.atenea.service.SocioEconomicServices;
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
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired @Qualifier("participantServices")
    private ParticipantServices participantServices;

    @Autowired @Qualifier("socioEconomicServices")
    private SocioEconomicServices socioEconomicServices;

    @PreAuthorize("hasRole('PARTICIPANT')")
    @PostMapping({"", "/"})
    public ResponseEntity addNewParticipant(@Valid @RequestBody ParticipantsDTO participantsDTO) throws URISyntaxException {
        ParticipantsDTO participantSaved = participantServices.save(participantsDTO);
        return ResponseEntity.created(new URI("/participants/" + participantsDTO.getId())).body(participantSaved);
    }

    @PreAuthorize("hasRole('PARTICIPANT')")
    @PostMapping({"/socioeconomics", "/socioeconomics/"}) // localhost:8080/socioeconomics && localhost:8080/socioeconomics/ [POST]
    public ResponseEntity addNewSocioEconomic(@Valid @RequestBody SocioEconomicDTO socioeconomicDTO) throws URISyntaxException {
        SocioEconomicDTO socioEconomicSaved = socioEconomicServices.save(socioeconomicDTO);
        return ResponseEntity.created(new URI("/socioeconomicsStudies/" + socioeconomicDTO.getId())).body(socioEconomicSaved);
    }

}
