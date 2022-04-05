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
@Table(name = "appusers")
public class User {
    @Id
    @SequenceGenerator(
            name = "appUser_sequence",
            sequenceName = "appUser_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "appUser_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long userId;

    @NotBlank
    @Column(name="username", nullable = false, unique = true)
    private String username;

    @Email
    @NotBlank
    @Column(name ="email", nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(name="password", nullable = false)
    private String password;

    @NotBlank
    @Column(name="firstName", nullable = false)
    private String firstName;

    @NotBlank
    @Column(name="lastName", nullable = false)
    private String lastName;

    @NotBlank
    @Pattern(regexp="^\\d{10}$", message="je mobiele nummer moet tien cijfers hebben")
    @Column(name="mobileNumber", nullable = false)
    private String mobileNumber;
//    TODO: RegEx for mobileNumber - NOW COMPLETED

    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false)
    private Role role;

}