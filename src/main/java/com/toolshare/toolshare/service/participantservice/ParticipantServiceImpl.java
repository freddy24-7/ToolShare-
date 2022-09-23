package com.toolshare.toolshare.service.participantservice;

import com.toolshare.toolshare.exception.BadRequestException;
import com.toolshare.toolshare.exception.ResourceNotFoundException;
import com.toolshare.toolshare.exception.UserNotFoundException;
import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.model.User;
import com.toolshare.toolshare.repository.ParticipantRepository;
import com.toolshare.toolshare.service.securityservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Participant> findAllParticipants() {
        return participantRepository.findAll();
    }

    @Override
    public Participant saveParticipant(Participant participant) {
        participant.setUser(userService.getLoggedInUser());
        Boolean existsEmail = Boolean.valueOf(participantRepository
                .selectExistsEmail(participant.getEmail()));

        if (existsEmail) {
            throw new BadRequestException(
                    "Email " + participant.getEmail() + " bestaat al");
        }
        return participantRepository.save(participant);
    }

    @Override
    public Participant findByLastName(String lastName) {
        return participantRepository.findByLastName(lastName);
    }

    @Override
    public Participant deleteParticipant(Long id) {
        if (!participantRepository.existsById(id)) {
            throw new UserNotFoundException(
                    "Lid met id " + id + " bestaat niet");
        }
        Participant participant = participantRepository.findById(id).orElseThrow(() -> new RuntimeException());
        participantRepository.delete(participant);
        return participant;
    }


    //previous version did not return a requestbody (was void) so changing the put-method
    //Using a ternary to check for existence of user, before changing values
    @Override
    public Participant updateParticipant(
            Participant participant, Long id)
    {
        Participant currenParticipant = getParticipantById(id);
        currenParticipant.setFirstName(participant.getFirstName() != null ? participant.getFirstName() : currenParticipant.getFirstName());
        currenParticipant.setLastName(participant.getLastName() != null ? participant.getLastName() : currenParticipant.getLastName());
        currenParticipant.setEmail(participant.getEmail() != null ? participant.getEmail() : currenParticipant.getEmail());
        currenParticipant.setMobileNumber(participant.getMobileNumber() != null ? participant.getMobileNumber() : currenParticipant.getMobileNumber());
        return participantRepository.save(currenParticipant);
    }

    @Override
    public Participant getParticipantById(Long id) {
        if (!participantRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Lid met id " + id + " bestaat niet");
        }
        Participant participant = participantRepository.findById(id).orElseThrow(() -> new RuntimeException())
                ;
        return participant;
    }


}



