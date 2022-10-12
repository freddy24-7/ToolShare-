package com.toolshare.toolshare.service.loanservice;

import com.toolshare.toolshare.model.LoanAction;

public interface LoanService {
    LoanAction saveLoanAction(Long id, LoanAction loanActionAddition);

    Object findLoanItemsOfParticipant(Long id);
}
