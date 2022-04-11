package com.toolshare.toolshare.service.loanservice;

import com.toolshare.toolshare.model.LoanAction;

public interface LoanService {
    LoanAction saveLoanAction(LoanAction loanAction);

    Object findLoanItemsOfParticipant(Long id);
}
