package com.toolshare.toolshare.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static javax.persistence.GenerationType.SEQUENCE;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity(name = "Participant")
@Table(name = "participant",
        uniqueConstraints = {
                @UniqueConstraint(name = "participant_email_unique",
                        columnNames = "email")
        }
)
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
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
    @Pattern(regexp="^\\d{10}$", message="je mobiele nummer moet tien cijfers hebben")
    @Column(name="mobileNumber", nullable = false)
    private String mobileNumber;
    //    TODO: RegEx for mobileNumber - NOW COMPLETED



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
//    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private User user;

    public Participant() {
    }

    public Participant(String email, String firstName, String lastName, String mobileNumber, User user) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
    }
}