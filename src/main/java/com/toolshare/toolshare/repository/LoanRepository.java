package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.model.LoanAction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoanRepository extends JpaRepository <LoanAction, Long> {

}
