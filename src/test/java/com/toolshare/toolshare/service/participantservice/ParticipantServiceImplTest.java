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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;


@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class ParticipantServiceImplTest {

    @Mock
    private ParticipantRepository participantRepository;

    //Saving participant is a complex method as it requires us to find the logged in user
    //We are here using tool to mock the logged-in user

    //Mocking the controller layer
    @Autowired
    private MockMvc mockMvc;

    //Building mockMvc
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    //Injecting the test class
    @InjectMocks
    private ParticipantServiceImpl underTest;

    //Mocking userService
    @Mock
    private UserServiceImpl userService;

    @Test
    void shouldSaveParticipantToDatabase() throws Exception {
        //Arrange
        mockMvc.perform(formLogin());
        Participant funnyParticipant = new Participant();
        funnyParticipant.setId(1L);
        funnyParticipant.setFirstName("tom");
        funnyParticipant.setLastName("hansen");
        funnyParticipant.setPhotoURL("myphoto.com");
        funnyParticipant.setEmail("tom@myphoto.com");
        funnyParticipant.setPostcode("3456HW");
        funnyParticipant.setMobileNumber("0909546543");
        //Act
        when(participantRepository.save(any(Participant.class))).thenReturn(funnyParticipant);
        Participant newParticipant = underTest.saveParticipant(funnyParticipant);
        //Assert
        assertNotNull(newParticipant);
        assertThat(newParticipant.getFirstName()).isEqualTo("tom");
    }

    @Test
    @Disabled
    void findByLastName() {
    }

    @Test
    @Disabled
    void deleteParticipant() {
    }

    @Test
    @Disabled
    void updateParticipant() {
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
    @Disabled
    void getParticipantById() {
    }


}