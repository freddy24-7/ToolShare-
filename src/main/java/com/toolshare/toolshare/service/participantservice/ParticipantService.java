package com.toolshare.toolshare.service.participantservice;

import com.toolshare.toolshare.model.Participant;

import java.util.List;

public interface ParticipantService {

    List<Participant> findAllParticipants();

    Participant saveParticipant(Participant participant);

    Participant findByLastName(String lastName);

    Participant deleteParticipant(Long participantId);

    void updateParticipant(Long participantId, String email, String emailPassword, String firstName, String lastName, String mobileNumber);
}
