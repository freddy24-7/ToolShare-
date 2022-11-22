package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository <Participant, Long> {
    
    Participant findByLastName(String lastName);

    //This method is used in the service class to link a save a participant under a specific user
    default String selectExistsEmail(String email) {
        return email;
    }
}
