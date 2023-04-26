
package com.toolshare.toolshare.model;

/* Using lombok to avoid boilerplate code */
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "Participant")
@Table(uniqueConstraints = {
                @UniqueConstraint(name = "participant_email_unique",
                        columnNames = "email")
        }
)
public class Participant {

    /**
     * The unique identifier for the Participant object.
     */
    @Id
    @SequenceGenerator(
            name = "participant_sequence",
            sequenceName = "participant_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "participant_sequence",
            strategy = SEQUENCE)
    @Column(updatable = false)
    private Long id;

    /**
     * Defining email, making it non-nullable.
     */
    @Column(nullable = false)
    private String email;

    /**
     * Defining first name, making it non-nullable.
     */
    @NotBlank
    @Column(nullable = false)
    private String firstName;

    /**
     * Defining last name, making it non-nullable.
     */
    @NotBlank
    @Column(nullable = false)
    private String lastName;

    /**
     * The postcode for this service. Must not be blank.
     */
    @NotBlank
    //Below RegeX pattern for the postcode area of this service
    @Pattern(regexp =
            "^[3][5][4][3] ?(?!sa|sd|ss|SA|SD|SS)([A-Z]{2}$|[a-z]{2}$)",
            message =
                    "U moet een geldige postcode invoeren die begint met 3543")
    @Column(nullable = false)
    private String postcode;

    /**
     * Defining photo URL.
     */
    @Column
    private String photoURL;

    /**
     * The mobile phone number of the participant.
     * This field is annotated with @NotBlank to ensure that it is not
     * null or empty. It is also annotated with @Pattern to ensure that
     * it consists of ten digits only.
     */
    @NotBlank
    @Pattern(
            regexp = "^\\d{10}$",
            message = "je mobiele nummer moet tien cijfers hebben")
    @Column(nullable = false)
    private String mobileNumber;

    /**
     * Creates a new Participant object with the given email,
     * first name, last name, postcode, photo URL, and mobile number.
     *
     * @param participantEmail The email address of the participant.
     * @param participantFirstName The first name of the participant.
     * @param participantLastName The last name of the participant.
     * @param participantPostcode The postcode of the participant's location.
     * @param participantPhotoURL The URL of the participant's profile photo.
     * @param participantMobileNumber The mobile phone
     * number of the participant.
     */

    public Participant(final String participantEmail,
                       final String participantFirstName,
                       final String participantLastName,
                       final String participantPostcode,
                       final String participantPhotoURL,
                       final String participantMobileNumber) {
        this.email = participantEmail;
        this.firstName = participantFirstName;
        this.lastName = participantLastName;
        this.postcode = participantPostcode;
        this.photoURL = participantPhotoURL;
        this.mobileNumber = participantMobileNumber;
    }
    /**
     * The User entity associated with this Participant.
     * This field is mapped as a OneToOne association
     * with the User table in the database,
     * using a foreign key named "participant_user_id_fk" and eagerly fetched.
     * All cascading operations are applied to the associated
     * User entity when they are performed on this Participant.
     */
    @OneToOne(
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "participant_user_id_fk"
            )
    )
    private User user;

    /**
     * The set of ShareItem entities associated with this Participant.
     * This field is mapped as a OneToMany association with
     * the ShareItem table in the database,
     * using a foreign key named "id" and lazy fetched.
     * All cascading operations are applied to the associated ShareItem
     * entities when they are performed on this Participant.
     * If an associated ShareItem entity is removed, it is also removed from
     * the set of ShareItem entities for this Participant.
     */
    @OneToMany(
            orphanRemoval = true,
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "id")
    private Set<ShareItem> items = new HashSet<>();

    /**
     * The set of LoanAction entities associated with this Participant.
     * This field is mapped as a OneToMany association with the LoanAction
     * table in the database,
     * using a foreign key named "id" and lazy fetched.
     * All cascading operations are applied to the associated LoanAction
     * entities when they are performed on this Participant.
     * If an associated LoanAction entity is removed, it is also removed from
     * the set of LoanAction entities for this Participant.
     */
    @OneToMany(
            orphanRemoval = true,
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "id")
    private Set<LoanAction> loanActions = new HashSet<>();

}
