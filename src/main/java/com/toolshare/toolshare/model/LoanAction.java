package com.toolshare.toolshare.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor@Entity
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

//    @Column(name="id", nullable = false)
//    private Long id;

//    //A participant can have several loans on in one time, so many to one
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
//    private Participant participant;

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    //An item can be lended out multiple times, so many to one
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "item_id", referencedColumnName = "itemId", insertable = false, updatable = false)
//    private ShareItem shareItem;

    @Column(name = "loanTime", nullable = false)
    private LocalDateTime loanTime;








}
