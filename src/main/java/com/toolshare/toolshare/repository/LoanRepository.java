
package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.model.LoanAction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Provides methods for accessing and manipulating
 * loan action data in the database.
 */
public interface LoanRepository extends JpaRepository<LoanAction, Long> {

}
