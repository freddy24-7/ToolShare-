package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.repository.ParticipantRepository;
import com.toolshare.toolshare.service.participantservice.ParticipantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/participant")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping
    public List<Participant> getAllParticipants() {
        return participantService.findAllParticipants();
    }

    @PostMapping
    public ResponseEntity<?> saveParticipant(@RequestBody Participant participant)
    {
        return new ResponseEntity<>(participantService.saveParticipant(participant), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{participantId}")
    public void deleteParticipant(
            @PathVariable("participantId") Long participantId) {
        participantService.deleteParticipant(participantId);
    }

    @PutMapping(path = "{participantId}")
    public void updateParticipant(
            @PathVariable("participantId") Long participantId,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String emailPassword,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String mobileNumber) {
        participantService.updateParticipant(participantId, email, emailPassword, firstName, lastName, mobileNumber);
    }
}