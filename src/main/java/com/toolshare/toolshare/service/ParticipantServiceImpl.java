
package com.toolshare.toolshare.service;

import com.toolshare.toolshare.exception.DuplicateEmailException;
import com.toolshare.toolshare.exception.ResourceNotFoundException;
import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.model.ShareItem;
import com.toolshare.toolshare.repository.ParticipantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

/**
 * Implementation of the {@link ParticipantService} interface.
 */
@Service
public class ParticipantServiceImpl implements ParticipantService {

    /**
     * The repository for accessing and modifying participant data
     * in the database.
     */
    @Autowired
    private ParticipantRepository participantRepository;

    /**
     * The service for managing user accounts and authentication.
     */
    @Autowired
    private UserService userService;


    /**
     * Retrieves all the participants from the repository.
     *
     * @return a list of {@link Participant} objects.
     */
    @Override
    public List<Participant> findAllParticipants() {
        return participantRepository.findAll();
    }

    /**
     * Saves a new {@link Participant} object to the repository.
     *
     * @param participant the {@link Participant} object to save.
     * @return the saved {@link Participant} object.
     * @throws DuplicateEmailException if the email of the {@link Participant}
     * object already exists in the repository.
     */
    @Override
    public Participant saveParticipant(final Participant participant) {
        participant.setUser(userService.getLoggedInUser());

        String postcode = participant.getPostcode();
        String mobileNumber = participant.getMobileNumber();
        int postCodeLength = 6;

        if (postcode != null && postcode.matches(
                "^3543[A-Z]{2}$") && postcode.length() <= postCodeLength) {
            if (mobileNumber != null && mobileNumber.matches("^06[0-9]{8}$")) {
                try {
                    return participantRepository.save(participant);
                } catch (DataIntegrityViolationException e) {
                    throw new DuplicateEmailException(
                            "Email " + participant.getEmail() + " bestaat al");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Mobiel nummer ongeldig. Moet beginnen met 06 en "
                                + "gevolgd worden door 8 cijfers.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Postcode ongeldig. Moet beginnen met 3543 en gevolgd "
                            + "worden door twee hoofdletters");
        }
    }

    /**
     * Deletes a {@link Participant} object from the repository.
     *
     * @param id the id of the {@link Participant} object to delete.
     */
    @Override
    public void deleteParticipant(final Long id) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Deelnemer niet gevonden met id = " + id));
        participantRepository.delete(participant);
    }

    /**
     * Updates an existing {@link Participant} object in the repository.
     *
     * @param participant the updated {@link Participant} object.
     * @param id          the id of the {@link Participant} object to update.
     * @return the updated {@link Participant} object.
     */
    @Override
    public Participant updateParticipant(
            final Participant participant,
            final Long id) {
        Participant currentParticipant = getParticipantById(id);

        String postcode = participant.getPostcode();
        String mobileNumber = participant.getMobileNumber();
        int postCodeLength = 6;

        if (postcode != null && postcode.matches("^3543[A-Z]{2}$")
                && postcode.length() <= postCodeLength) {
            if (mobileNumber != null && mobileNumber.matches("^06[0-9]{8}$")) {
                try {
                    currentParticipant
                            .setFirstName(participant.getFirstName() != null
                                    ? participant.getFirstName()
                                    : currentParticipant.getFirstName());
                    currentParticipant
                            .setLastName(participant.getLastName() != null
                                    ? participant.getLastName()
                                    : currentParticipant.getLastName());
                    currentParticipant
                            .setPostcode(participant.getPostcode() != null
                                    ? participant.getPostcode()
                                    : currentParticipant.getPostcode());
                    currentParticipant
                            .setPhotoURL(participant.getPhotoURL() != null
                                    ? participant.getPhotoURL()
                                    : currentParticipant.getPhotoURL());
                    currentParticipant
                            .setEmail(participant.getEmail() != null
                                    ? participant.getEmail()
                                    : currentParticipant.getEmail());
                    currentParticipant
                            .setMobileNumber(participant
                                    .getMobileNumber() != null
                                    ? participant.getMobileNumber()
                                    : currentParticipant.getMobileNumber());
                    return participantRepository.save(currentParticipant);
                } catch (DataIntegrityViolationException e) {
                    throw new DuplicateEmailException(
                            "Email " + participant.getEmail() + " bestaat al");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Mobiel nummer ongeldig. Moet beginnen met 06 en "
                                + "gevolgd worden door 8 cijfers");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ongeldige postcode. Moet beginnen met 3543 en gevolgd "
                            + "worden door twee hoofdletters.");
        }
    }

    /**
     * Deletes all {@link ShareItem} objects of a {@link Participant} object.
     *
     * @param participantId the id of the {@link Participant} object w
     * whose {@link ShareItem} objects are to be deleted.
     * @throws ResourceNotFoundException if the {@link Participant}
     * object is not
     * found in the repository.
     */
    @Override
    public void deleteAllItemsOfParticipant(final Long participantId) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Deelnemer niet gevonden met id = " + participantId));
        participant.getItems().clear();
        participantRepository.save(participant);
    }

    /**
     * Retrieves all the {@link ShareItem} objects of
     * a {@link Participant} object.
     *
     * @param participantId the id of the {@link Participant} object
     *                      whose {@link ShareItem} objects are to be retrieved.
     * @return the {@link Participant} object with all
     * its {@link ShareItem} objects.
     * @throws ResourceNotFoundException if the {@link Participant} object
     * is not found in the repository.
     */
    @Override
    public Participant getAllItemsByParticipantId(final Long participantId) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Deelnemer niet gevonden met id = " + participantId));
        Set<ShareItem> participantItems = participant.getItems();
        return participant;
    }

    /**
     * Retrieves a {@link Participant} object by id.
     *
     * @param id the id of the {@link Participant} object to retrieve.
     * @return the retrieved {@link Participant} object.
     * @throws RuntimeException if the {@link Participant}
     * object is not found in the repository.
     */
    @Override
    public Participant getParticipantById(final Long id) {
        Participant participant = participantRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Deelnemer niet gevonden met id " + id));
        return participant;
    }
}
