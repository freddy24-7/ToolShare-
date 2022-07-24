package com.toolshare.toolshare.model;

import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "participants")
public class Participant {
    @Id
    @SequenceGenerator(
            name = "participant_sequence",
            sequenceName = "participant_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "participant_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long id;

    @Email
    @Column(name="email", nullable = false, unique = true)
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

}