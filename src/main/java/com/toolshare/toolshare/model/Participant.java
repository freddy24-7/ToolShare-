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

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Entity(name = "Participant")
@Table(name = "participant",
        uniqueConstraints = {
                @UniqueConstraint(name = "participant_email_unique",
                        columnNames = "email")
        }
)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
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

    @Column(name="photoURL")
    private String photoURL;

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
    private User user;

    @OneToMany(
            mappedBy = "participant",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<ShareItem> items = new ArrayList<>();

//    public Participant() {
//    }

//    public Participant(String email, String firstName, String lastName, String photoURL, String mobileNumber, User user, List<ShareItem> items) {
//        this.email = email;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.photoURL = photoURL;
//        this.mobileNumber = mobileNumber;
//        this.user = user;
//        this.items = items;
//    }

//    public List<ShareItem> getItems() {
//        return items;
//    }
//
//    public void setItems(List<ShareItem> items) {
//        this.items = items;
//    }

//    public void addItem(ShareItem shareItem) {
//        if (!this.items.contains(shareItem)) {
//            this.items.add(shareItem);
//            shareItem.setParticipant(this);
//        }
//    }

//    public void removeItem(ShareItem shareItem) {
//        if (this.items.contains(shareItem)) {
//            this.items.remove(shareItem);
//            shareItem.setParticipant(null);
//        }
//    }

}