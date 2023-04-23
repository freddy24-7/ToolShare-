
package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides methods for accessing and manipulating
 * participant data in the database.
 */
@Repository
public interface ParticipantRepository extends JpaRepository
        <Participant, Long> {

    /**
     * Finds a participant with the specified last name, if it exists.
     *
     * @param lastName the last name to search for
     * @return the participant with the specified last
     * name, or null if it does not exist
     */
    Participant findByLastName(String lastName);

    /**
     * A default method that returns the email string passed as an argument.
     * This method is provided as an example and does
     * not interact with the database.
     *
     * @param email the email to return
     * @return the email passed as an argument
     */
    default String selectExistsEmail(String email) {
        return email;
    }
}
