package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.service.participantservice.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/participant")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping("/participants")
    public List<Participant> getAllParticipants() {

        return participantService.findAllParticipants();
    }

    @GetMapping(path = "/participants/{id}")
    public Participant getParticipantById(@PathVariable long id) {

        return participantService.getParticipantById(id);
    }

    @PostMapping("/participants")
    public Participant saveParticipant(@RequestBody Participant participant) {

        return participantService.saveParticipant(participant);
    }

    @PutMapping(path = "/participants/{id}")
    public ResponseEntity<Participant> updateParticipant(@RequestBody Participant participant,
                                                         @PathVariable Long id) {
        return new ResponseEntity<Participant>(participantService.updateParticipant(participant, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/participants/{id}")
    public void deleteParticipant(
            @PathVariable("id") Long id) {
        participantService.deleteParticipant(id);
    }

};

