
package com.toolshare.toolshare.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loan_actions")
public class LoanAction {

    /**
     * The unique identifier for the LoanAction object.
     */
    @Id
    @SequenceGenerator(
            name = "loan_sequence",
            sequenceName = "loan_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "loan_sequence",
            strategy = GenerationType.SEQUENCE)
    @Column(name = "loan_id")
    private Long loanId;

    /**
     * The unique identifier for the ShareItem object
     * associated with this LoanAction.
     */
    @Column(name = "item_id")
    private Long itemId;

    /**
     * The time when this loan action was created.
     */
    @Column(name = "loan_time")
    private LocalDateTime loanTime;
}
