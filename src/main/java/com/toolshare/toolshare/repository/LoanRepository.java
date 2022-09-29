package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.model.LoanAction;
import com.toolshare.toolshare.repository.projection.LoanItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanRepository extends JpaRepository <LoanAction, Long> {

    //We want to find item and date
    @Query("select " +
              "sha.itemName as name, loa.loanTime as LoanTime " +
               "from LoanAction loa left join ShareItem sha on sha.itemId = loa.itemId " +
               "where loa.id = :id")

    List<LoanItem> findAllLoansOfUser(@Param("id") Long id);

}
