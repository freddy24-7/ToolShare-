
package com.toolshare.toolshare.service.loanservice;

import com.toolshare.toolshare.model.LoanAction;
import com.toolshare.toolshare.model.Participant;

public interface LoanService {


    /**
     * Saves a loan action to a participant's loan history.
     *
     * @param id the ID of the participant to save the loan action to
     * @param loanActionAddition the loan action
     * to add to the participant's loan history
     * @return the saved loan action
     */
    LoanAction saveLoanAction(Long id, LoanAction loanActionAddition);

    /**
     * Gets a participant's loan history by ID.
     *
     * @param id the ID of the participant whose loan history to get
     * @return the participant with the specified ID
     * and their associated loan history
     */
    Participant getLoanActionById(Long id);
}
