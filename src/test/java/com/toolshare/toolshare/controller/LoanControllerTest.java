package com.toolshare.toolshare.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toolshare.toolshare.model.LoanAction;
import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.security.CustomUserDetailsService;
import com.toolshare.toolshare.security.JwtProviderImpl;
import com.toolshare.toolshare.service.LoanServiceImpl;
import com.toolshare.toolshare.service.ParticipantServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Setting up the controller-class that is being tested
@WebMvcTest(LoanController.class)
//Autoconfiguring to MockMvc with addFilters = false to prevent 403-error
@AutoConfigureMockMvc(addFilters = false)
class LoanControllerTest {

    //Mocking the required service with Mockbean
    @MockBean
    private ParticipantServiceImpl participantService;

    @MockBean
    private LoanServiceImpl loanService;

    //Mocking these two service below as they are required for the application context
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
    void shouldSaveLoanAction() throws Exception {

        //Arrange
        Participant funnyParticipant = new Participant();
        funnyParticipant.setId(1L);

        LoanAction aNewLoan = new LoanAction();
        aNewLoan.setLoanId(3L);
        aNewLoan.setItemId(2L);
        aNewLoan.setLoanTime(LocalDateTime.now());
        //Act
        when(loanService.saveLoanAction(1L, aNewLoan)).thenReturn(aNewLoan);
        this.mockMvc.perform(post("http://localhost:8080/api/loan/participant/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(aNewLoan)))
                //Assert
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetLoanActionById() throws Exception {

        //Arrange
        Participant funnyParticipant = new Participant();
        funnyParticipant.setId(1L);

        LoanAction aNewLoan = new LoanAction();
        aNewLoan.setLoanId(3L);
        aNewLoan.setItemId(2L);
        aNewLoan.setLoanTime(LocalDateTime.now());
        //Act & stubbing the method
        when(loanService.getLoanActionById(anyLong())).thenReturn(funnyParticipant);
        this.mockMvc.perform(get("http://localhost:8080/api/loan/participants/loaninterest/{id}", 3L))
                //assert
                .andExpect(status().isOk());
    }
}