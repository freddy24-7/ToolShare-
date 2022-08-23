package com.toolshare.toolshare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @SequenceGenerator(
            name = "User_sequence",
            sequenceName = "User_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "User_sequence",
            strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Transient
    private String token;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(name = "participant_id", referencedColumnName = "id", nullable = true)
    private Participant participant;

    public User() {
    }

    public User(String username, String password, String name, LocalDateTime createTime, Role role, String token, Participant participant) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.createTime = createTime;
        this.role = role;
        this.token = token;
    }
}



