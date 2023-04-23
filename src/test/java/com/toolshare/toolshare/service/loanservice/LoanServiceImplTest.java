package com.toolshare.toolshare.service.loanservice;

import com.toolshare.toolshare.model.LoanAction;
import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.model.ShareItem;
import com.toolshare.toolshare.repository.LoanRepository;
import com.toolshare.toolshare.repository.ParticipantRepository;
import com.toolshare.toolshare.service.LoanServiceImpl;
import com.toolshare.toolshare.service.ParticipantServiceImpl;
import com.toolshare.toolshare.service.UserServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanServiceImplTest {

    //Mocking required repositories
    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private LoanRepository loanRepository;

    //Injecting test classes
    @InjectMocks
    private ParticipantServiceImpl participantService;

    @InjectMocks
    private LoanServiceImpl loanService;

    //Mocking userService for authentication
    @Mock
    private UserServiceImpl userService;

    @Test
    void saveLoanAction() {
        //Arrange
        LoanAction aNewLoan = new LoanAction();
        aNewLoan.setLoanId(3L);
        aNewLoan.setItemId(2L);
        aNewLoan.setLoanTime(LocalDateTime.now());

        Participant funnyParticipant = new Participant();
        funnyParticipant.setId(5L);

        ShareItem newItem = new ShareItem();
        newItem.setItemId(2L);
        newItem.setItemName("Drill");
        newItem.setDescription("Make holes");
        newItem.setPhotoURL("www.photoMe.com");
        System.out.println(funnyParticipant.getId());
        //Act
        when(participantRepository.save(any(Participant.class))).thenReturn(funnyParticipant);
        Participant newParticipant = participantService.saveParticipant(funnyParticipant);
        loanRepository.save(aNewLoan);
        newParticipant.getItems().add(newItem);
        newParticipant.getLoanActions().add(aNewLoan);
        participantService.saveParticipant(newParticipant);
        System.out.println(newParticipant);
        //Assert
        assertNotNull(loanRepository.findAll());
        assertNotNull(newParticipant.getLoanActions());
        assertTrue(newParticipant.getLoanActions().contains(aNewLoan));
    }

    @Test
    @Disabled
    void getLoanActionById() {
    }
}
