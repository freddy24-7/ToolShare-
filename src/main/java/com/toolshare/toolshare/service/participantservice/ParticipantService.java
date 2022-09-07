package com.toolshare.toolshare.service.participantservice;

import com.toolshare.toolshare.model.Participant;

import java.util.List;
import java.util.Map;

public interface ParticipantService {

    List<Participant> findAllParticipants();

    Participant saveParticipant(Participant participant);

    Participant findByLastName(String lastName);

    Participant deleteParticipant(Long id);

//
//    void updateParticipant(Long id, String email, String firstName, String lastName, String mobileNumber);
//

    Participant getParticipantById(Long id);

    Participant updateParticipant(Participant participant, Long id);

}
