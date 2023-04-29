
package com.toolshare.toolshare.service;

import com.toolshare.toolshare.model.Participant;
import java.util.List;

public interface ParticipantService {

    /**
     * Returns a list of all participants.
     *
     * @return a list of all participants
     */
    List<Participant> findAllParticipants();

    /**
     * Saves a participant to the database.
     *
     * @param participant the participant to save
     * @return the saved participant
     */
    Participant saveParticipant(Participant participant);

    /**
     * Gets a participant by ID.
     *
     * @param id the ID of the participant to get
     * @return the participant with the specified ID, or null if not found
     */
    Participant getParticipantById(Long id);

    /**
     * Updates a participant in the database.
     *
     * @param participant the participant to update
     * @param id the ID of the participant to update
     * @return the updated participant
     */
    Participant updateParticipant(Participant participant, Long id);

    /**
     * Deletes all items of a participant from the database.
     *
     * @param participantId the ID of the participant whose items to delete
     */
    void deleteAllItemsOfParticipant(Long participantId);

    /**
     * Gets all items of a participant by ID.
     *
     * @param id the ID of the participant whose items to get
     * @return the participant with the specified ID and their
     * associated items, or null if not found
     */
    Participant getAllItemsByParticipantId(Long id);

    /**
     * Deletes a participant from the database by ID.
     *
     * @param id the ID of the participant to delete
     */
    void deleteParticipant(Long id);

}
