package ar.com.ada.atenea.controller;

import ar.com.ada.atenea.model.dto.ParticipantsDTO;
import ar.com.ada.atenea.service.ParticipantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
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

    @GetMapping({"", "/"})
    public ResponseEntity getAllParticipants() {
        List<ParticipantsDTO> all = participantServices.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping({"/{id}", "/{id}/"}) // localhost:8080/participants/{id} && localhost:8080/participants/{id}/ [GET]
    public ResponseEntity getParticipantById(@PathVariable Long id) {
        ParticipantsDTO participantsById = participantServices.findParticipantsById(id);
        return ResponseEntity.ok(participantsById);
    }

    @PostMapping({"", "/"})
    public ResponseEntity addNewParticipant(@Valid @RequestBody ParticipantsDTO participantsDTO) throws URISyntaxException {
        ParticipantsDTO participantSaved = participantServices.save(participantsDTO);
        return ResponseEntity.created(new URI("/participants/" + participantsDTO.getId())).body(participantSaved);
    }

    @PutMapping({"/{id}", "/{id}/"})
    public ResponseEntity updateParticipantById(@Valid @RequestBody ParticipantsDTO participantsDTO, @PathVariable Long id) {
        ParticipantsDTO updateParticipant = participantServices.updateParticipant(participantsDTO, id);
        return ResponseEntity.ok(updateParticipant);
    }

    @DeleteMapping({"", "/"})
    public ResponseEntity deleteParticipant(@PathVariable Long id) {
        participantServices.delete(id);
        return ResponseEntity.noContent().build();
    }

}
