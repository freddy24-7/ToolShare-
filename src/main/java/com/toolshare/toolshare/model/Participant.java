package com.toolshare.toolshare.model;

import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Participant")
@Table(name = "participant",
        uniqueConstraints = {
                @UniqueConstraint(name = "participant_email_unique",
                        columnNames = "email")
        }
)

public class Participant {
    @Id
    @SequenceGenerator(
            name = "participant_sequence",
            sequenceName = "participant_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "participant_sequence",
            strategy = SEQUENCE)
    @Column(name = "id",
            updatable = false)
    private Long id;

    @Email
    @Column(name="email", nullable = false)
    private String email;

    @NotBlank
    @Column(name="firstname", nullable = false)
    private String firstName;

    @NotBlank
    @Column(name="lastname", nullable = false)
    private String lastName;

    @NotBlank
    @Column(name="photoURL", nullable = false)
    private String photoURL;

    @NotBlank
    @Pattern(regexp="^\\d{10}$", message="je mobiele nummer moet tien cijfers hebben")
    @Column(name="mobileNumber", nullable = false)
    private String mobileNumber;

    //Note that these fields are needed in the constructor below, lombok takes care of no arg and all arg
    public Participant(String email, String firstName, String lastName, String photoURL, String mobileNumber) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photoURL = photoURL;
        this.mobileNumber = mobileNumber;
    }

    @OneToOne(
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

    //One participant (class name Participant), and many items per participant
    @OneToMany(
            orphanRemoval = true,
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "participant_id")
    private Set<ShareItem> items = new HashSet<>();

    //One participant (class name Participant), and many loanActions per participant
    @OneToMany(
            orphanRemoval = true,
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "participant_id")
    private Set<LoanAction> loanActions = new HashSet<>();

}