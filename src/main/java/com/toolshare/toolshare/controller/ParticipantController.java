
package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.service.ParticipantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "This API retrieves a list of all participants.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of participants retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
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
    @Operation(summary = "This API retrieves a specific participant "
            + "based on the id of the participant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "participant details retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description =
                            "Participant not found with id:"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
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
    @Operation(summary = "This API signs up a new participant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Participant created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "409",
                    description = "Email address already exists"),
            @ApiResponse(responseCode = "400",
                    description = "Validation (Bad Request) error on "
                            + "postcode or mobile number"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
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
    @Operation(summary = "This API updates the details of a participant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Participant details successfully updated",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "409",
                    description = "Email address chosen already exists"),
            @ApiResponse(responseCode = "400",
                    description = "Validation (Bad Request) error on "
                            + "postcode or mobile number"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    public ResponseEntity<Participant> updateParticipant(
            @Valid @RequestBody final Participant participant,
            @PathVariable final Long id) {
        return new ResponseEntity<>(
                participantService.updateParticipant(participant, id),
                HttpStatus.OK);
    }

    /**
     * Deletes a participant from the database by ID.
     *
     * @param id the ID of the participant to delete
     */
    @DeleteMapping(path = "/participants/{id}")
    @Operation(summary = "This API allows the deletion of a participant object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Participant object successfully deleted",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "No participant object found for that id"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    public void deleteParticipant(@PathVariable final Long id) {
        participantService.deleteParticipant(id);
    }

}
