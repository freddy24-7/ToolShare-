
package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.model.ShareItem;
import com.toolshare.toolshare.service.ItemService;
import com.toolshare.toolshare.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/items")
public class ItemController {

    /**
     * The item service where the business logic for items takes place.
     */
    @Autowired
    private ItemService itemService;

    /**
     * The participant service where the business logic
     * for participants takes place.
     */
    @Autowired
    private ParticipantService participantService;

    /**
     * Returns a list of all items in the database.
     *
     * @return a list of all items
     */
    @GetMapping("/items")
    public List<ShareItem> getAllItems() {
        return itemService.findAllItems();
    }

    /**
     * Gets an item by ID.
     *
     * @param itemId the ID of the item to get
     * @return a response entity containing the item and a success
     * status code, or not found status if the item
     * cannot be found
     */
    @GetMapping("/items/{itemId}")
    public ResponseEntity<ShareItem> getShareItemsById(
            @PathVariable final Long itemId) {
        ShareItem item = itemService.getShareItemsById(itemId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    /**
     * Gets all items submitted by a participant.
     *
     * @param id the ID of the participant whose items to get
     * @return the participant with the specified ID
     * and their associated items, or null if not found
     */
    @GetMapping("/participants/items/{id}")
    public Participant getAllItemsByParticipantId(
            @PathVariable final Long id) {
        return participantService.getAllItemsByParticipantId(id);
    }

    /**
     * Saves an item to the database for the specified participant.
     *
     * @param id the ID of the participant submitting the item
     * @param addShareItem the item to submit
     * @return the newly saved item
     */
    @PostMapping("/participants/items/{id}")
    public ShareItem createShareItem(
            @PathVariable final Long id,
            @RequestBody final ShareItem addShareItem) {
        return itemService.createShareItem(id, addShareItem);
    }

    /**
     * Updates an item in the database.
     *
     * @param itemId the ID of the item to update
     * @param updateItem the updated item
     * @return a response entity containing the updated item
     * and a success status code, or not found status if the
     * item cannot be found
     */
    @PutMapping("/items/{itemId}")
    public ResponseEntity<ShareItem> updateShareItem(
            @PathVariable final long itemId,
            @RequestBody final ShareItem updateItem) {
        return new ResponseEntity<>(
                itemService.updateShareItem(itemId, updateItem), HttpStatus.OK);
    }

    /**
     * Deletes an item from the database by ID.
     *
     * @param itemId the ID of the item to delete
     */
    @DeleteMapping(path = "{itemId}")
    public void deleteItem(@PathVariable final Long itemId) {
        itemService.deleteItem(itemId);
    }
    /**
     * Deletes all items submitted by a participant from the database.
     *
     * @param id the ID of the participant whose items to delete
     * @return a response entity with a success status code
     */
    @DeleteMapping("/participants/{id}/items")
    public ResponseEntity<List<ShareItem>> deleteAllItemsOfParticipant(
            @PathVariable final Long id) {
        participantService.deleteAllItemsOfParticipant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
