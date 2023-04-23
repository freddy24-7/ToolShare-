
package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/participant")
public class ParticipantController {

    /**
     * The participant service where the business logic takes place.
     */
    @Autowired
    private ParticipantService participantService;

    /**
     * Returns a list of all participants.
     *
     * @return a list of all participants
     */
    @GetMapping("/participants")
    public List<Participant> getAllParticipants() {
        return participantService.findAllParticipants();
    }

    /**
     * Gets a participant by ID.
     *
     * @param id the ID of the participant to get
     * @return the participant with the specified ID, or null if not found
     */
    @GetMapping(path = "/participants/{id}")
    public Participant getParticipantById(@PathVariable final long id) {
        return participantService.getParticipantById(id);
    }

    /**
     * Saves a participant to the database.
     *
     * @param participant the participant to save
     * @return the saved participant
     */
    @PostMapping("/participants")
    public Participant saveParticipant(
            @RequestBody final Participant participant) {
        return participantService.saveParticipant(participant);
    }

    /**
     * Updates a participant in the database.
     *
     * @param participant the participant to update
     * @param id the ID of the participant to update
     * @return a response entity containing the updated
     * participant and a success status code
     */
    @PutMapping(path = "/participants/{id}")
    public ResponseEntity<Participant> updateParticipant(
            @Valid @RequestBody final Participant participant,
            @PathVariable final Long id) {
        return new ResponseEntity<Participant>(
                participantService.updateParticipant(participant, id),
                HttpStatus.OK);
    }

    /**
     * Deletes a participant from the database by ID.
     *
     * @param id the ID of the participant to delete
     */
    @DeleteMapping(path = "/participants/{id}")
    public void deleteParticipant(@PathVariable final Long id) {
        participantService.deleteParticipant(id);
    }

}
