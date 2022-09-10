package com.toolshare.toolshare.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "username_unique",
                        columnNames = "username")
        }
)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_sequence")
    @Column(name = "id",
            updatable = false)
    private Long id;

    @NotBlank
    @Column(name = "username", nullable = false, columnDefinition = "TEXT")
    private String username;

    @NotBlank
    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Transient
    private String token;

//    @OneToOne(
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true,
//            fetch = FetchType.LAZY
//    )
//    private Participant participant;

    public User() {
    }

    public User(String username, String password, LocalDateTime createTime, Role role, String token, Participant participant) {
        this.username = username;
        this.password = password;
        this.createTime = createTime;
        this.role = role;
        this.token = token;
    }
}



