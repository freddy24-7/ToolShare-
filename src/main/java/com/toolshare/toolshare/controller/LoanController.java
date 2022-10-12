package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.model.LoanAction;
import com.toolshare.toolshare.model.ShareItem;
import com.toolshare.toolshare.security.UserPrinciple;
import com.toolshare.toolshare.service.loanservice.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

//    @PostMapping("/participant/{id}")
//    public ResponseEntity<?> saveLoan(@PathVariable Long id,
//                                      @RequestBody LoanAction loanAction)
//    {
//        return new ResponseEntity<>(loanService.saveLoanAction(id, loanAction), HttpStatus.CREATED);
//    }

    @PostMapping("/participant/{id}")
    public LoanAction saveLoanAction(@PathVariable Long id,
                                     @RequestBody LoanAction addLoanAction) {
        return loanService.saveLoanAction(id, addLoanAction);

    }

    @GetMapping
    public ResponseEntity<?> getAllLoansOfUser(@AuthenticationPrincipal UserPrinciple userPrinciple)
    {
        return ResponseEntity.ok(loanService.findLoanItemsOfParticipant(userPrinciple.getId()));
    }

}
