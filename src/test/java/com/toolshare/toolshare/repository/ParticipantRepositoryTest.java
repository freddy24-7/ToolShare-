package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.model.Participant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//Below annotation required for testing repository
@DataJpaTest
class ParticipantRepositoryTest {

    @Autowired
    private ParticipantRepository underTest;

    @Test
    void itShouldCheckExistsEmail() {
        //Arrange
        String email = "tommmy@gmail.com";
        Participant participant = new Participant(
                email,
                "tommy",
                "hansen",
                "myphto@webmail.com",
                "0909878743"
        );
        underTest.save(participant);
        //Act
        String exists = underTest.selectExistsEmail(email);
        //Assert
        assertThat(exists).isNotEmpty();
    }
    @Test
    void itShouldSaveDataToDatabase() {
        //Assert
        Participant funnyParticipant = new Participant();
        funnyParticipant.setFirstName("tom");
        funnyParticipant.setLastName("hansen");
        funnyParticipant.setPhotoURL("myphoto.com");
        funnyParticipant.setPostcode("3456HW");
        funnyParticipant.setMobileNumber("0909546543");
        //Act
        Participant newParticipant = underTest.save(funnyParticipant);
        //Assert
        assertNotNull(newParticipant);
        assertThat(newParticipant.getId()).isNotEqualTo(null);

    }
    @Test
    void itShouldGetAllParticipants() {
        //Arrange
        Participant funnyParticipant = new Participant();
        funnyParticipant.setId(1L);
        funnyParticipant.setFirstName("tom");
        funnyParticipant.setLastName("hansen");
        funnyParticipant.setPhotoURL("myphoto.com");
        funnyParticipant.setEmail("tom@myphoto.com");
        funnyParticipant.setPostcode("3456HW");
        funnyParticipant.setMobileNumber("0909546543");
        underTest.save(funnyParticipant);

        List<Participant> list = underTest.findAll();
        //Assert
        assertNotNull(list);
        assertThat(list).isNotNull();
        assertEquals(1, list.size());
    }
}