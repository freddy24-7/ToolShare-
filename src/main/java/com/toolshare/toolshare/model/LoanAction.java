package com.toolshare.toolshare.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "loan_actions")
public class LoanAction {

    @Id
    @SequenceGenerator(
            name = "loan_sequence",
            sequenceName = "loan_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "loan_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long loanId;

    @Column(name="id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)//It is only for foreign key.
    private Participant participant;

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", referencedColumnName = "itemId", insertable = false, updatable = false)
    private ShareItem shareItem;

    @Column(name = "loanTime", nullable = true)
    private LocalDateTime loanTime;


}
