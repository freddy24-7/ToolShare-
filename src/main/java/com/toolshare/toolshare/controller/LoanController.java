package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.model.LoanAction;
import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.service.loanservice.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/loan")
public class LoanController {

    //Autowiring the service class where the business logic takes place
    @Autowired
    private LoanService loanService;

    //PostMapping for clicking on owner details of an item
    @PostMapping("/participant/{id}")
    public LoanAction saveLoanAction(@PathVariable Long id,
                                     @RequestBody LoanAction addLoanAction) {
        return loanService.saveLoanAction(id, addLoanAction);

    }

    //GetMapping to obtain earlier times an interest was indicated in an item by a participant
    @GetMapping("/participants/loaninterest/{id}")
    public Participant getLoanActionById(@PathVariable Long id) {

        return loanService.getLoanActionById(id);

    }

}