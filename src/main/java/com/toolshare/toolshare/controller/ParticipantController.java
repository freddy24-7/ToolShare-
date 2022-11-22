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

    //"Participant" and "User" are in this application separated for the sake of convenience
    //"User" handles spring security and security configurations, including JWT creation
    //"Participant" handles all transactions, and just passes the JWT token that was created by "User"
    //A 1-1 mapping between the two is handled in the models package

    //Autowiring the service class where the business logic takes place
    @Autowired
    private ParticipantService participantService;

    //GetMapping to obtain a list of all participants in the application
    @GetMapping("/participants")
    public List<Participant> getAllParticipants() {

        return participantService.findAllParticipants();
    }

    //Getting specific participant by id
    @GetMapping(path = "/participants/{id}")
    public Participant getParticipantById(@PathVariable long id) {

        return participantService.getParticipantById(id);
    }

    //PostMapping to post a new participant into the application
    @PostMapping("/participants")
    public Participant saveParticipant(@RequestBody Participant participant) {

        return participantService.saveParticipant(participant);
    }

    //PutMapping to allow the participant to modify his or her details
    @PutMapping(path = "/participants/{id}")
    public ResponseEntity<Participant> updateParticipant(@RequestBody Participant participant,
                                                         @PathVariable Long id) {
        return new ResponseEntity<Participant>(participantService.updateParticipant(participant, id), HttpStatus.OK);
    }

    //DeleteMapping that allows the participant to delete his/her details in the application
    @DeleteMapping(path = "/participants/{id}")
    public void deleteParticipant(
            @PathVariable Long id) {
        participantService.deleteParticipant(id);
    }

};