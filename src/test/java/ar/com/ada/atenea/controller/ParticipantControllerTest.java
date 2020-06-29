package ar.com.ada.atenea.controller;

import ar.com.ada.atenea.model.dto.ParticipantsDTO;
import ar.com.ada.atenea.model.entity.Participant;
import ar.com.ada.atenea.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.atenea.model.mapper.ParticipantCycleMapper;
import ar.com.ada.atenea.model.repository.ParticipantRepository;
import ar.com.ada.atenea.service.ParticipantServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParticipantControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private ParticipantCycleMapper participantCycleMapper = ParticipantCycleMapper.MAPPER;

    @Autowired
    @Qualifier("participantServices")
    private ParticipantServices participantServices;

    @Autowired
    @Qualifier("participantRepository")
    private ParticipantRepository participantRepository;

    @Test
    public void shouldBeCreatedANewParticipant() {
        String url = "http://localhost:" + port + "/participants";

        ParticipantsDTO newParticipant = new ParticipantsDTO()
                .setName("Sofia")
                .setLastName("Maidana")
                .setLocation("Calle falsa 123")
                .setBirthday(new Date(1998-9-11))
                .setNumberDoc(10200300);

        //HttpHeaders headers = new HttpHeaders();
        HttpEntity<ParticipantsDTO> request = new HttpEntity<>(newParticipant);

        //WHEN
        ResponseEntity<ParticipantsDTO> response = testRestTemplate.exchange(
                url, HttpMethod.POST, request, new ParameterizedTypeReference<ParticipantsDTO>() {
                }
        );

        // FORMA A: expected vs actual
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, response.getBody().getId());
    }

    @Test
    public void returnParticipantListWhenFindAll() {

        ParticipantsDTO participantMock = new ParticipantsDTO()
                .setId(1L)
                .setName("sof")
                .setLastName("maida")
                .setLocation("calle falsa 123")
                .setBirthday(new Date(1998-9-12))
                .setNumberDoc(20300500);

        ParticipantsDTO participantMock2 = new ParticipantsDTO()
                .setId(2L)
                .setName("sof2")
                .setLastName("maida2")
                .setLocation("calle falsa 1223")
                .setBirthday(new Date(1998-9-13))
                .setNumberDoc(40300500);

        Participant participantToSave = participantCycleMapper.toEntity(participantMock, context);
        Participant participantToSaveTwo = participantCycleMapper.toEntity(participantMock2, context);

        participantRepository.save(participantToSave);
        participantRepository.save(participantToSaveTwo);

        List<ParticipantsDTO> all = participantServices.findAll();

        assertThat(all.size()).isEqualTo(2);

    }


}