package ar.com.ada.atenea.model.repository;

import ar.com.ada.atenea.model.entity.Participant;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ParticipantRepositoryTest {

    @Autowired
    @Qualifier("participantRepository")
    private ParticipantRepository participantRepository;

    @Test
    @Order(0)
    public void whenSaveThenReturnAnParticipantWithId() {
        //GIVEN
        Participant participant = new Participant()
                .setName("Maria")
                .setLastName("Alvarez")
                .setLocation("Capital")
                .setNumberDoc(40100200);
        //WHEN
        Participant saved = participantRepository.save(participant);

        //THEN
        assertNotNull(saved.getId());
    }

    @Test @Order(1)
    public void whenFindAllThenReturnParticipantList() {
        //GIVEN

        //WHEN
        List<Participant> participantList = participantRepository.findAll();

        //THEN
        assertThat(participantList).hasSizeGreaterThan(1);
    }

}