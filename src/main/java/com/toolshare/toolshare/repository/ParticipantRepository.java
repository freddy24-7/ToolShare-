package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository <Participant, Long> {
    
    Participant findByLastName(String lastName);

    Optional<Participant> findParticipantByEmail(String email);

    default String selectExistsEmail(String email) {
        return email;
    }
}
