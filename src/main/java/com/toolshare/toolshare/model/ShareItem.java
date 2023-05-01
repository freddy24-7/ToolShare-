
package com.toolshare.toolshare.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ShareItem")
@Table(name = "shareitem")
public class ShareItem {

    /**
     * The unique identifier for the ShareItem object.
     */
    @Id
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "item_sequence",
            strategy = SEQUENCE)
    @Column(name = "item_id", updatable = false)
    private Long itemId;

    /**
     * Defining item name, making it non-nullable.
     */
    @Column
    private String itemName;

    /**
     * Defining description, making it non-nullable.
     */
    @Column
    private String description;

    /**
     * Defining photo URL.
     */
    @Column
    private String photoURL;

    /**
     * The set of LoanAction entities associated with this ShareItem.
     * This field is mapped as a OneToMany association with
     * the LoanAction table in the database,
     * using a foreign key named "item_id" and lazy fetched.
     * All cascading operations are applied to the associated LoanAction
     * entities when they are performed on this ShareItem.
     * If an associated LoanAction entity is removed, it is also removed from
     * the set of LoanAction entities for this ShareItem.
     */
    @OneToMany(
            orphanRemoval = true,
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "item_id")
    private Set<LoanAction> loanActions = new HashSet<>();

    /**
     * Creates a new ShareItem object with the given item name, description,
     * photo URL, and loan actions.
     *
     * @param shareItemName The name of the item.
     * @param shareItemDescription The description of the item.
     * @param shareItemPhotoURL The URL of the item's photo.
     * @param shareItemLoanActions The set of loan actions
     * associated with the item.
     */
    public ShareItem(final String shareItemName,
                     final String shareItemDescription,
                     final String shareItemPhotoURL,
                     final Set<LoanAction> shareItemLoanActions) {
        this.itemName = shareItemName;
        this.description = shareItemDescription;
        this.photoURL = shareItemPhotoURL;
        this.loanActions = shareItemLoanActions;
    }
}
