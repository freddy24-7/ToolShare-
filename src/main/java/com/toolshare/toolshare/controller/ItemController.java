package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.model.ShareItem;
import com.toolshare.toolshare.service.itemservice.ItemService;
import com.toolshare.toolshare.service.participantservice.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/items")

public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ParticipantService participantService;

    @GetMapping("/items")
    public List<ShareItem> getAllItems() {
        return itemService.findAllItems();
    }

    @GetMapping("/items/{itemId}")
    public ResponseEntity<ShareItem> getShareItemsById(@PathVariable Long itemId) {
        ShareItem item = itemService.getShareItemsById(itemId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/participants/{id}/items")
    public Participant getAllItemsByParticipantId(@PathVariable Long id) {

        return participantService.getAllItemsByParticipantId(id);

    }

    @PostMapping("/participants/items/{id}")
    public ShareItem createShareItem(@PathVariable Long id,
                             @RequestBody ShareItem addShareItem) {
    return itemService.createShareItem(id, addShareItem);
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<ShareItem> updateShareItem(@PathVariable long itemId, @RequestBody ShareItem updateItem) {

        return new ResponseEntity<>(itemService.updateShareItem(itemId, updateItem), HttpStatus.OK);
    }

    @DeleteMapping(path = "{itemId}") public void deleteItem(@PathVariable Long itemId) {

        itemService.deleteItem(itemId);
    }

    @DeleteMapping("/participants/{id}/items")
    public ResponseEntity<List<ShareItem>> deleteAllItemsOfParticipant(
            @PathVariable Long id) {
        participantService.deleteAllItemsOfParticipant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}