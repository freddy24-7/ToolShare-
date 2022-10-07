package com.toolshare.toolshare.service.participantservice;

import com.toolshare.toolshare.model.Participant;
import java.util.List;

public interface ParticipantService {

    List<Participant> findAllParticipants();

    Participant saveParticipant(Participant participant);

    Participant findByLastName(String lastName);

    Participant getParticipantById(Long id);

    Participant updateParticipant(Participant participant, Long id);

    void deleteAllItemsOfParticipant(Long participantId);

    Participant getAllItemsByParticipantId(Long id);

    void deleteParticipant(Long id);

}
