
package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.model.ShareItem;
import com.toolshare.toolshare.service.ItemService;
import com.toolshare.toolshare.service.ParticipantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping(path = "/api/")
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
    @Operation(summary = "This API retrieves a list of all uploaded items.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of share items retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
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
    @Operation(summary = "This API retrieves a specific item based "
            + "on an item id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Item successfully retrieved",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Item not found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
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
    @Operation(summary = "This API retrieves the list of items uploaded by a "
            + "particular user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of share items uploaded by a specific "
                            + "participant retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "List of share items uploaded by specific "
                            + "participant not found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
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
    @Operation(summary = "This API allows a participant to upload "
            + "a share item. A participant can upload several items, "
            + "although one at a time")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Share item successfully uploaded",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "Bad request. Neither item name, "
                            + "description or photo can be null",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
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
    @Operation(summary = "This API allows a participant edit "
            + "a share item that the participant has earlier uploaded")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Share item successfully edited",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "Bad request. Neither item name, "
                            + "description or photo can be null",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
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
    @DeleteMapping(path = "/items/{itemId}")
    @Operation(summary = "This API allows the deletion of a share item object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "share item object successfully deleted",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "No share item object found for that id"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
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
    @Operation(summary = "This API allows the deletion of all "
            + "share items earlier uploaded by a participant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "all items belonging to participant deleted",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "No participant found for that id"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    public ResponseEntity<List<ShareItem>> deleteAllItemsOfParticipant(
            @PathVariable final Long id) {
        participantService.deleteAllItemsOfParticipant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
