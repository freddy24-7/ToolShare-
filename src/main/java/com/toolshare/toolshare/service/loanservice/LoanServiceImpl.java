package com.toolshare.toolshare.service.loanservice;

import com.toolshare.toolshare.model.LoanAction;
import com.toolshare.toolshare.repository.LoanRepository;
import com.toolshare.toolshare.repository.projection.LoanItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public LoanAction saveLoanAction(LoanAction loanAction) {
        loanAction.setLoanTime(LocalDateTime.now());

        return loanRepository.save(loanAction);
    }

    public List<LoanItem> findLoanItemsOfParticipant(Long id) {
        return loanRepository.findAllLoansOfUser(id);
    }

}
