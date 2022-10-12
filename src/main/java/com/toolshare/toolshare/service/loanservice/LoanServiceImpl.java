package com.toolshare.toolshare.service.loanservice;

import com.toolshare.toolshare.exception.ResourceNotFoundException;
import com.toolshare.toolshare.model.LoanAction;
import com.toolshare.toolshare.model.ShareItem;
import com.toolshare.toolshare.repository.LoanRepository;
import com.toolshare.toolshare.repository.ParticipantRepository;
import com.toolshare.toolshare.repository.projection.LoanItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ParticipantRepository participantRepository;


    @Override
    public LoanAction saveLoanAction(Long id, LoanAction loanActionAddition) {
        //mapping through the participantobject to get all existing items before adding one to the
        //items list
        LoanAction loanAction = participantRepository.findById(id).map(participant -> {
            participant.getLoanActions().add(loanActionAddition);
            return loanRepository.save(loanActionAddition);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Participant with id = " + id));
        return loanAction;
    }

    public List<LoanItem> findLoanItemsOfParticipant(Long id) {
        return loanRepository.findAllLoansOfUser(id);
    }

}
