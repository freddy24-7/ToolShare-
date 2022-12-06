package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.model.LoanAction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//Below annotation required for testing repository
@DataJpaTest
class LoanRepositoryTest {

    @Autowired
    private LoanRepository underTest;

    @Test
    void itShouldSaveDataToDatabase() {
        //Assert
        LoanAction borrowThis = new LoanAction();
        borrowThis.setLoanId(1L);
        //Act
        LoanAction newLoan = underTest.save(borrowThis);
        //Assert
        assertNotNull(newLoan);
        assertThat(newLoan.getLoanId()).isNotEqualTo(null);

    }

}