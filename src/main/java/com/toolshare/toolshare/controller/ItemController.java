package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.model.ShareItem;
import com.toolshare.toolshare.service.itemservice.ItemService;
import com.toolshare.toolshare.service.participantservice.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/items")

public class ItemController {

    //Autowiring the service classes where the business logic takes place
    @Autowired
    private ItemService itemService;

    @Autowired
    private ParticipantService participantService;

    //GetMapping to access all items uploaded into the database (items are here the "things" than can be
    //shared among the users of the applications)
    @GetMapping("/items")
    public List<ShareItem> getAllItems() {
        return itemService.findAllItems();
    }

    //GetMapping to obtain a specific item based on the id of that item. Is used in frontend for example when a
    //user has chosen a specific item that he or she wants to look at in detail.
    @GetMapping("/items/{itemId}")
    public ResponseEntity<ShareItem> getShareItemsById(@PathVariable Long itemId) {
        ShareItem item = itemService.getShareItemsById(itemId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    //GetMapping where all items are listed with the id of the user submitting them (or lending them out)
    @GetMapping("/participants/items/{id}")
    public Participant getAllItemsByParticipantId(@PathVariable Long id) {

        return participantService.getAllItemsByParticipantId(id);

    }

    //PostMapping where a user can submit an item that he or she wants to lend out.
    @PostMapping("/participants/items/{id}")
    public ShareItem createShareItem(@PathVariable Long id,
                                     @RequestBody ShareItem addShareItem) {
        return itemService.createShareItem(id, addShareItem);
    }

    //This mapping can be used to update the item - like change its description for example
    @PutMapping("/items/{itemId}")
    public ResponseEntity<ShareItem> updateShareItem(@PathVariable long itemId, @RequestBody ShareItem updateItem) {

        return new ResponseEntity<>(itemService.updateShareItem(itemId, updateItem), HttpStatus.OK);
    }

    //Deletes a specific item submitted by a user
    @DeleteMapping(path = "{itemId}") public void deleteItem(@PathVariable Long itemId) {

        itemService.deleteItem(itemId);
    }

    //Deletes all items that have been submitted by a user.
    @DeleteMapping("/participants/{id}/items")
    public ResponseEntity<List<ShareItem>> deleteAllItemsOfParticipant(
            @PathVariable Long id) {
        participantService.deleteAllItemsOfParticipant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}