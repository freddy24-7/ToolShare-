package com.toolshare.toolshare.service.participantservice;

import com.toolshare.toolshare.exception.BadRequestException;
import com.toolshare.toolshare.exception.UserNotFoundException;
import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public List<Participant> findAllParticipants() {
        return participantRepository.findAll();
    }

    @Override
    public Participant saveParticipant(Participant participant) {
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
    public Participant deleteParticipant(Long participantId) {
        if (!participantRepository.existsById(participantId)) {
            throw new UserNotFoundException(
                    "Lid met id " + participantId + " bestaat niet");
        }
        Participant participant = participantRepository.findById(participantId).orElseThrow(() -> new RuntimeException());
        participantRepository.delete(participant);
        return participant;
    }

    @Transactional
    public void updateParticipant(Long participantId,
                           String email,
                           String emailPassword,
                           String firstName,
                           String lastName,
                           String mobileNumber) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new IllegalStateException(
                        "lid met id " + participantId + " bestaat niet."));

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(participant.getEmail(), email)) {
            Optional<Participant> participantOptional = participantRepository
                    .findParticipantByEmail(email);
            if (participantOptional.isPresent()) {
                throw new IllegalStateException("email bestaat al");
            }
            participant.setEmail(email);

        }
        if (emailPassword != null &&
                emailPassword.length() > 0 &&
                !Objects.equals(participant.getEmailPassword(), emailPassword)) {
            participant.setEmailPassword(emailPassword);
        }

        if (firstName != null &&
                firstName.length() > 0 &&
                !Objects.equals(participant.getFirstName(), firstName)) {
            participant.setFirstName(firstName);
        }
        if (lastName != null &&
                lastName.length() > 0 &&
                !Objects.equals(participant.getLastName(), lastName)) {
            participant.setLastName(lastName);
        }
        if (mobileNumber != null &&
                mobileNumber.length() > 0 &&
                !Objects.equals(participant.getMobileNumber(), mobileNumber)) {
            participant.setMobileNumber(mobileNumber);
        }

    }

}



