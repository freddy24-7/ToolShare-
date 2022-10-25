package com.toolshare.toolshare.service.participantservice;

import com.toolshare.toolshare.exception.BadRequestException;
import com.toolshare.toolshare.exception.ResourceNotFoundException;
import com.toolshare.toolshare.exception.UserNotFoundException;
import com.toolshare.toolshare.model.Participant;

import com.toolshare.toolshare.model.ShareItem;
import com.toolshare.toolshare.repository.ItemRepository;
import com.toolshare.toolshare.repository.ParticipantRepository;
import com.toolshare.toolshare.service.securityservice.UserService;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Participant> findAllParticipants() {

        return participantRepository.findAll();
    }

    @Override
    public Participant saveParticipant(Participant participant) {
        //The below line is essential for finding the logged in user before saving the participant
        //and all the attributes of the participant. Ensures referential integrity in the 1-1 relationship
        //between user and participant
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
    public void deleteParticipant(Long id) {
        if (!participantRepository.existsById(id)) {
            throw new UserNotFoundException(
                    "Lid met id " + id + " bestaat niet");
        }
        Participant participant = participantRepository.findById(id).orElseThrow(() -> new RuntimeException());
        participantRepository.delete(participant);
    }

    //Earlier versions did not return a requestbody (was void) so changing the put-method
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
    public void deleteAllItemsOfParticipant(Long participantId) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Participant with id = " + participantId));
        //Accessing the participant, getting all the items, then deleting them:
        participant.getItems().clear();
        participantRepository.save(participant);
    }

    @Override
    public Participant getAllItemsByParticipantId(Long participantId) {
        Participant Participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Participant with id = " + participantId));
        List<ShareItem> items = new ArrayList<ShareItem>();
        items.addAll(Participant.getItems());

        return Participant;
    }

    @Override
    public Participant getParticipantById(Long id) {
        if (!participantRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Lid met id " + id + " bestaat niet");
        }
        Participant participant = participantRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return participant;
    }



}



