
package com.toolshare.toolshare.service;

import com.toolshare.toolshare.exception.ResourceNotFoundException;
import com.toolshare.toolshare.model.ShareItem;
import com.toolshare.toolshare.repository.ItemRepository;
import com.toolshare.toolshare.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .findById(itemId).orElseThrow(() -> new RuntimeException());
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
        ShareItem itemUpdate = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "ShareItemId " + itemId + "niet gevonden"));
        itemUpdate.setItemName(shareItemEdit.getItemName());
        itemUpdate.setDescription(shareItemEdit.getDescription());
        itemRepository.save(itemUpdate);
        return itemUpdate;
    }
}
