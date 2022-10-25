package com.toolshare.toolshare.service.loanservice;

import com.toolshare.toolshare.model.LoanAction;
import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.model.ShareItem;

public interface LoanService {
    LoanAction saveLoanAction(Long id, LoanAction loanActionAddition);


    Participant getLoanActionById (Long id);

}
