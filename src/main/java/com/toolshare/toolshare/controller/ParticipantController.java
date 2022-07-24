package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.service.participantservice.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
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
    public ResponseEntity<?> saveParticipant(@RequestBody Participant participant) {
        return new ResponseEntity<>(participantService.saveParticipant(participant), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{id}")
    public void deleteParticipant(
            @PathVariable("id") Long id) {
        participantService.deleteParticipant(id);
    }

    @GetMapping(path = "{id}")
    public Participant getParticipantById(@PathVariable long id) {
        return participantService.getParticipantById(id);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Participant> updateParticipant(@RequestBody Participant participant,
                                                         @PathVariable Long id) {
        return new ResponseEntity<Participant>(participantService.updateParticipant(participant, id), HttpStatus.OK);
    }
};

