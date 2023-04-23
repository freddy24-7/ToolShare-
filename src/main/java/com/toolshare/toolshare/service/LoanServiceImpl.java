
package com.toolshare.toolshare.service;

import com.toolshare.toolshare.exception.ResourceNotFoundException;
import com.toolshare.toolshare.model.LoanAction;
import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.repository.LoanRepository;
import com.toolshare.toolshare.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    /**
     * The repository for managing loan actions.
     */
    @Autowired
    private LoanRepository loanRepository;

    /**
     * The repository for managing participants.
     */
    @Autowired
    private ParticipantRepository participantRepository;

    /**
     * Saves a loan action to a participant's loan history.
     *
     * @param id the ID of the participant to save the loan action to
     * @param loanActionAddition the loan action to add
     * to the participant's loan history
     * @return the saved loan action
     * @throws ResourceNotFoundException if the participant
     * with the given ID is not found
     */
    @Override
    public LoanAction saveLoanAction(
            final Long id, final LoanAction loanActionAddition) {
        loanActionAddition.setLoanTime(LocalDateTime.now());
        LoanAction loanAction = participantRepository
                .findById(id).map(participant -> {
            participant.getLoanActions().add(loanActionAddition);

            return loanRepository.save(loanActionAddition);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Deelnemer niet gevonden met id = " + id));
        return loanAction;
    }

    /**
     * Gets a participant's loan history by ID.
     *
     * @param id the ID of the participant whose loan history to get
     * @return the participant with the specified ID
     * and their associated loan history
     * @throws ResourceNotFoundException if the participant
     * with the given ID is not found
     */
    @Override
    public Participant getLoanActionById(final Long id) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Deelnemer niet gevonden met id = " + id));
        List<LoanAction> loanActions = new ArrayList<LoanAction>();
        loanActions.addAll(participant.getLoanActions());
        return participant;
    }
}
