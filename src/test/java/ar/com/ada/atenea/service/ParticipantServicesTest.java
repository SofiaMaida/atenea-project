package ar.com.ada.atenea.service;

import ar.com.ada.atenea.model.dto.ParticipantsDTO;
import ar.com.ada.atenea.model.entity.Participant;
import ar.com.ada.atenea.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.atenea.model.repository.ParticipantRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ParticipantServicesTest {

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private CycleAvoidingMappingContext context;

    @InjectMocks
    private ParticipantServices participantServices;

    @Test
    public void whenFindAllThenReturnParticipantList() {
        //GIVEN
        Participant particpantMock = new Participant()
                .setId(1L)
                .setName("cris")
                .setLastName("gomez")
                .setLocation("calle falsa 1234")
                .setNumberDoc(50300500);


        Participant participantMock2 = new Participant()
                .setId(2L)
                .setName("eduardo")
                .setLastName("lopez")
                .setLocation("calle falsa 12345")
                .setNumberDoc(70300500);


        // Lista de entrega fake
        List<Participant> participantListMock = Arrays.asList(particpantMock, participantMock2);

        when(participantRepository.findAll()).thenReturn(participantListMock);

        //WHEN
        List<ParticipantsDTO> participantDTOList = participantServices.findAll();

        //THEN
        assertThat(participantDTOList.size()).isEqualTo(2);
        assertThat(participantDTOList.get(0).getName()).isEqualTo("cris");
    }

    @Test
    public void whenSaveReturnParticipantDtoSaved() {
        //GIVEN
        Participant participantMock = new Participant()
                .setId(1L)
                .setName("Borky")
                .setLastName("Perez")
                .setLocation("calle falsa 1235")
                .setNumberDoc(70600500);

        when(participantRepository.save(any(Participant.class))).thenReturn(participantMock);
        ParticipantsDTO dto = new ParticipantsDTO();

        //WHEN
        ParticipantsDTO dtoSaved = participantServices.save(dto);

        //THEN
        assertThat(dtoSaved.getId()).isEqualTo(1);
    }


}