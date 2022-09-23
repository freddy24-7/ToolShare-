package com.toolshare.toolshare.service.participantservice;

import com.toolshare.toolshare.model.Participant;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ParticipantService {

    List<Participant> findAllParticipants();

    Participant saveParticipant(Participant participant);

    Participant findByLastName(String lastName);

    Participant deleteParticipant(Long id);

    Participant getParticipantById(Long id);

    Participant updateParticipant(Participant participant, Long id);


}
