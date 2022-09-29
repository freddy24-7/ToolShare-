package com.toolshare.toolshare.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Set;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shareitem")
public class ShareItem {
    @Id
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "item_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long itemId;

    @NotBlank
    @Column(name = "itemName", nullable = false)
    private String itemName;

    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    //A participant can contribute several items as lend-items, so many to one
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Participant participant;

    //An item can have many loan actions, so one to many
    //Bidirectional relationship
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shareItem")
    private Set<LoanAction> loanList;




}