
package com.toolshare.toolshare.service;

import com.toolshare.toolshare.model.ShareItem;
import java.util.List;

/**
 * Service interface for managing share items.
 */
public interface ItemService {

    /**
     * Deletes an item from the database by ID.
     *
     * @param itemId the ID of the item to delete
     */
    void deleteItem(Long itemId);

    /**
     * Gets a list of all items.
     *
     * @return a list of all items
     */
    List<ShareItem> findAllItems();

    /**
     * Gets an item by ID.
     *
     * @param itemId the ID of the item to get
     * @return the item with the specified ID
     */
    ShareItem getShareItemsById(Long itemId);

    /**
     * Creates an item and associates it with a participant.
     *
     * @param id the ID of the participant to associate the item with
     * @param shareItemAddition the item to create
     * @return the created item
     */
    ShareItem createShareItem(Long id, ShareItem shareItemAddition);

    /**
     * Updates an existing item in the database.
     *
     * @param itemId the ID of the item to update
     * @param shareItemEdit the updated item information
     * @return the updated item
     */
    ShareItem updateShareItem(long itemId, ShareItem shareItemEdit);
}
