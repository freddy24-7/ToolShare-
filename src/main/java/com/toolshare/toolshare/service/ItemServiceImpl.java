
package com.toolshare.toolshare.service;

import com.toolshare.toolshare.exception.BadRequestException;
import com.toolshare.toolshare.exception.ResourceNotFoundException;
import com.toolshare.toolshare.model.ShareItem;
import com.toolshare.toolshare.repository.ItemRepository;
import com.toolshare.toolshare.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    /**
     * The repository for managing share items.
     */
    @Autowired
    private ItemRepository itemRepository;

    /**
     * The repository for managing participants.
     */
    @Autowired
    private ParticipantRepository participantRepository;

    /**
     * Deletes an item from the database by ID.
     *
     * @param itemId the ID of the item to delete
     */
    @Override
    public void deleteItem(final Long itemId) {
        ShareItem shareItem = itemRepository
                .findById(itemId).orElseThrow(() -> new ResourceNotFoundException(
                "Share item niet gevonden met id = " + itemId));
        itemRepository.delete(shareItem);
    }

    /**
     * Gets a list of all items.
     *
     * @return a list of all items
     */
    @Override
    public List<ShareItem> findAllItems() {
        return itemRepository.findAll();
    }

    /**
     * Gets an item by ID.
     *
     * @param itemId the ID of the item to get
     * @return the item with the specified ID
     * @throws ResourceNotFoundException if the
     * item with the given ID is not found
     */
    @Override
    public ShareItem getShareItemsById(final Long itemId) {
        ShareItem shareItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "ShareItem niet gevonden met id = " + itemId));
        return shareItem;
    }

    /**
     * Creates an item and associates it with a participant.
     *
     * @param id the ID of the participant to associate the item with
     * @param shareItemAddition the item to create
     * @return the created item
     * @throws ResourceNotFoundException if the participant
     * with the given ID is not found
     */
    @Override
    public ShareItem createShareItem(
            final Long id,
            final ShareItem shareItemAddition) {
        if (shareItemAddition.getItemName() == null
                ||
                shareItemAddition.getDescription() == null
                ||
                shareItemAddition.getPhotoURL() == null) {
            throw new BadRequestException("Neither itemName, description"
                    + ", or photoURL can be null");
        }
        ShareItem shareItem = participantRepository
                .findById(id).map(participant -> {
                    participant.getItems().add(shareItemAddition);
                    return itemRepository.save(shareItemAddition);
                }).orElseThrow(() -> new ResourceNotFoundException(
                        "Deelnemer niet gevonden met id " + id));
        return shareItem;
    }

    /**
     * Updates an existing item in the database.
     *
     * @param itemId the ID of the item to update
     * @param shareItemEdit the updated item information
     * @return the updated item
     * @throws ResourceNotFoundException if the item with
     * the given ID is not found
     */
    @Override
    public ShareItem updateShareItem(final long itemId,
                                     final ShareItem shareItemEdit) {
        try {
            if (shareItemEdit.getItemName() == null
                    || shareItemEdit.getItemName().isEmpty()
                    || shareItemEdit.getDescription() == null
                    || shareItemEdit.getDescription().isEmpty()
                    || shareItemEdit.getPhotoURL() == null
                    || shareItemEdit.getPhotoURL().isEmpty()) {
                throw new IllegalArgumentException("Neither item name, "
                        + "description, nor photo url can be null or empty");
            }
            ShareItem itemUpdate = itemRepository.findById(itemId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "ShareItemId " + itemId + "niet gevonden"));
            itemUpdate.setItemName(shareItemEdit.getItemName());
            itemUpdate.setDescription(shareItemEdit.getDescription());
            itemUpdate.setPhotoURL(shareItemEdit.getPhotoURL());
            itemRepository.save(itemUpdate);
            return itemUpdate;
        } catch (NullPointerException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "photoURL cannot be null", ex);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "An unexpected error occurred", ex);
        }
    }
}
