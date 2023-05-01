package com.toolshare.toolshare.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.security.CustomUserDetailsService;
import com.toolshare.toolshare.security.JwtProviderImpl;
import com.toolshare.toolshare.service.ParticipantServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Setting up the controller-class that is being tested
@WebMvcTest(ParticipantController.class)
//Autoconfiguring to MockMvc with addFilters = false to prevent 403-error
@AutoConfigureMockMvc(addFilters = false)
class ParticipantControllerTest {

    //Mocking the required service with Mockbean
    @MockBean
    private ParticipantServiceImpl participantService;

    //Mocking these two services below as they are required for the application context
    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private JwtProviderImpl jwtProvider;

    //MockMvc allows the testing of the controller layer without the use of an http-server
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetAllParticipants() throws Exception {

        Participant funnyParticipant = new Participant();
        funnyParticipant.setId(1L);
        funnyParticipant.setFirstName("tom");
        funnyParticipant.setLastName("hansen");
        funnyParticipant.setPhotoURL("myphoto.com");
        funnyParticipant.setEmail("tom@myphoto.com");
        funnyParticipant.setPostcode("3543HW");
        funnyParticipant.setMobileNumber("0909546543");

        Participant topParticipant = new Participant();
        topParticipant.setId(2L);
        topParticipant.setFirstName("tommy");
        topParticipant.setLastName("Smith");
        topParticipant.setPhotoURL("allphoto.com");
        topParticipant.setEmail("smith@gmail.com.com");
        topParticipant.setPostcode("3543AA");
        topParticipant.setMobileNumber("0909878732");

        List<Participant> participantList = new ArrayList<>();
        participantList.add(funnyParticipant);
        participantList.add(topParticipant);

        when(participantService.findAllParticipants()).thenReturn(participantList);

        this.mockMvc.perform(get("http://localhost:8080/api/participants"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetParticipantById() throws Exception {

        //Arrange
        Participant funnyParticipant = new Participant();
        funnyParticipant.setId(1L);
        funnyParticipant.setFirstName("tom");
        funnyParticipant.setLastName("hansen");
        funnyParticipant.setPhotoURL("myphoto.com");
        funnyParticipant.setEmail("tom@myphoto.com");
        funnyParticipant.setPostcode("3543HW");
        funnyParticipant.setMobileNumber("0909546543");
        //Act & stubbing the method
        when(participantService.getParticipantById(anyLong())).thenReturn(funnyParticipant);
        this.mockMvc.perform(get("http://localhost:8080/api/participants/{id}", 1L))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    void shouldSaveParticipant() throws Exception {

        //Arrange
        Participant funnyParticipant = new Participant();
        funnyParticipant.setId(1L);
        funnyParticipant.setFirstName("tom");
        funnyParticipant.setLastName("hansen");
        funnyParticipant.setPhotoURL("myphoto.com");
        funnyParticipant.setEmail("tom@myphoto.com");
        funnyParticipant.setPostcode("3543HW");
        funnyParticipant.setMobileNumber("0909546543");
        //Act - and stub method
        when(participantService.saveParticipant(any(Participant.class))).thenReturn(funnyParticipant);
        this.mockMvc.perform(post("http://localhost:8080/api/participants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funnyParticipant)))
                //Assert
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateParticipant() throws Exception {

        //Arrange
        Participant funnyParticipant = new Participant();
        funnyParticipant.setId(1L);
        funnyParticipant.setFirstName("tom");
        funnyParticipant.setLastName("hansen");
        funnyParticipant.setPhotoURL("myphoto.com");
        funnyParticipant.setEmail("tom@myphoto.com");
        funnyParticipant.setPostcode("3543HW");
        funnyParticipant.setMobileNumber("0909546543");
        //Act - and stub method
        when(participantService.updateParticipant(any(Participant.class), anyLong())).thenReturn(funnyParticipant);

        this.mockMvc.perform(put("http://localhost:8080/api/participants/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(funnyParticipant)))
                //Assert
                .andExpect(status().isOk());
    }

    @Test
    void deleteParticipant() throws Exception {

        //Arrange
        Participant funnyParticipant = new Participant();
        funnyParticipant.setId(1L);
        funnyParticipant.setFirstName("tom");
        funnyParticipant.setLastName("hansen");
        funnyParticipant.setPhotoURL("myphoto.com");
        funnyParticipant.setEmail("tom@myphoto.com");
        funnyParticipant.setPostcode("3543HW");
        funnyParticipant.setMobileNumber("0909546543");
        //Act
        doNothing().when(participantService).deleteParticipant(anyLong());
        //Assert
        this.mockMvc.perform(delete("http://localhost:8080/api/participants/{id}", 1L))
                .andExpect(status().isOk());
    }
}