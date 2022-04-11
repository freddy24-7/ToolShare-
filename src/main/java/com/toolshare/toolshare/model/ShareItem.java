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

    @NotBlank
    @Pattern(regexp="^\\d{10}$", message="je nummer moet tien cijfers hebben")
    @Column(name="phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shareitem")
//    private Set<LoanAction> loanList;




}