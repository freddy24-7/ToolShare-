package com.toolshare.toolshare.service.participantservice;

import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.repository.ParticipantRepository;
import com.toolshare.toolshare.service.securityservice.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class ParticipantServiceImplTest {

    @Mock
    private ParticipantRepository participantRepository;

    //Injecting the test class
    @InjectMocks
    private ParticipantServiceImpl underTest;

    //Mocking userService
    @Mock
    private UserServiceImpl userService;

    //reference variables used for a variety of the below tests
    private Participant funnyParticipant;
    private Participant topParticipant;

    @BeforeEach
    void init() {
        funnyParticipant = new Participant();
        funnyParticipant.setId(1L);
        funnyParticipant.setFirstName("tom");
        funnyParticipant.setLastName("hansen");
        funnyParticipant.setPhotoURL("myphoto.com");
        funnyParticipant.setEmail("tom@myphoto.com");
        funnyParticipant.setPostcode("3456HW");
        funnyParticipant.setMobileNumber("0909546543");

        topParticipant = new Participant();
        topParticipant.setId(2L);
        topParticipant.setFirstName("tommy");
        topParticipant.setLastName("Smith");
        topParticipant.setPhotoURL("allphoto.com");
        topParticipant.setEmail("smith@gmail.com.com");
        topParticipant.setPostcode("3489AA");
        topParticipant.setMobileNumber("0909878732");
    }

    @Test
    void shouldSaveParticipantToDatabase() {
        //Arrange
        //Reference variables are created above, rule 41 to 63
        //Act
        when(participantRepository.save(any(Participant.class))).thenReturn(funnyParticipant);
        Participant newParticipant = underTest.saveParticipant(funnyParticipant);
        //Assert
        assertNotNull(newParticipant);
        assertThat(newParticipant.getFirstName()).isEqualTo("tom");
    }

    @Test
    void shouldFindAllParticipants() throws Exception {
        //Reference variables are created above, rule 41 to 63
        //Arrange
        List<Participant> participantList = new ArrayList<>();
        participantList.add(funnyParticipant);
        participantList.add(topParticipant);
        //Act
        when(participantRepository.findAll()).thenReturn(participantList);
        List<Participant> participants = underTest.findAllParticipants();
        //Assert
        assertEquals(2, participants.size());
        assertNotNull(participants);
    }

    @Test
    @Disabled
    void findByLastName() {
    }

    @Test
    void shouldDeleteParticipant() {
        //Arrange
        //Reference variables are created above, rule 41 to 63
        //Act
        when(participantRepository.findById(anyLong())).thenReturn(Optional.of(topParticipant));
        doNothing().when(participantRepository).delete(any(Participant.class));
        underTest.deleteParticipant(2L);
        //Assert
        verify(participantRepository, times(1)).delete(topParticipant);

    }

    @Test
    void shouldUpdateParticipantAndSaveToDatabase() {
        //Arrange
        //Reference variables are created above, rule 41 to 63
        //Act
        when(participantRepository.findById(anyLong())).thenReturn(Optional.of(topParticipant));
        when(participantRepository.save(any(Participant.class))).thenReturn(topParticipant);
        topParticipant.setFirstName("Edgar");
        Participant updatedParticipant = underTest.updateParticipant(topParticipant, 2L);
        //Assert
        assertNotNull(updatedParticipant);
        assertEquals("Edgar", updatedParticipant.getFirstName());
    }

    @Test
    @Disabled
    void deleteAllItemsOfParticipant() {
    }

    @Test
    @Disabled
    void getAllItemsByParticipantId() {
    }

    @Test
    void shouldGetAParticipantById() {
        //Arrange
        //Reference variables are created above, rule 41 to 63
        //Act
        when(participantRepository.findById(anyLong())).thenReturn(Optional.of(topParticipant));
        Participant oneMoreParticipant = underTest.getParticipantById(2L);
        //Assert
        assertNotNull(oneMoreParticipant);
        assertThat(oneMoreParticipant.getId()).isEqualTo(2L);
    }

    @Test
    void shouldThrowExceptionWhenWrongIdCalled() {
        //Arrange
        //Reference variables are created above, rule 41 to 63
        //Act
        when(participantRepository.findById(2L)).thenReturn(Optional.of(topParticipant));
        //Assert
        assertThrows(RuntimeException.class, () -> {
            underTest.getParticipantById(1L);
        });
    }

}