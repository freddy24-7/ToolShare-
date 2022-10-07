package com.toolshare.toolshare.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.SEQUENCE;

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
            strategy = SEQUENCE)
    private Long itemId;

    @NotBlank
    @Column(name = "itemName", nullable = false)
    private String itemName;

    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;

}