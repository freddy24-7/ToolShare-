
package com.toolshare.toolshare.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "User")
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "username_unique",
                        columnNames = "username")
        }
)
public class User {

    /**
     * The unique identifier for the User object.
     */
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "user_sequence",
            strategy = GenerationType.SEQUENCE)
    @Column(updatable = false)
    private Long id;

    /**
     * Defining username, making it non-nullable.
     */
    @NotBlank
    @Column(nullable = false)
    private String username;

    /**
     * Defining password, making it non-nullable.
     */
    @NotBlank
    @Column(nullable = false)
    private String password;

    /**
     * The time at which this user was created.
     */
    private Instant createTime;

    /**
     * The role of this user.
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * The JWT token associated with this user.
     */
    private String token;

    /**
     * Creates a new User object with the given
     * username, password, role, and token.
     *
     * @param userUsername The username of the user.
     * @param userPassword The password of the user.
     * @param userRole The role of the user.
     * @param userToken The JWT token associated with the user.
     */
    public User(final String userUsername,
                final String userPassword,
                final Role userRole,
                final String userToken) {
        this.username = userUsername;
        this.password = userPassword;
        this.role = userRole;
        this.token = userToken;
        this.createTime = Instant.now();
    }
}
