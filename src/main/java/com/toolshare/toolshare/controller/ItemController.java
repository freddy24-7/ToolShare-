package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.model.ShareItem;
import com.toolshare.toolshare.service.itemservice.ItemService;
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

    @GetMapping
    public List<ShareItem> getAllItems() {
        return itemService.findAllItems();
    }

    @PostMapping
    public ResponseEntity<?> saveItem(@RequestBody ShareItem shareItem)
    {
        return new ResponseEntity<>(itemService.saveItem(shareItem), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{itemId}")
    public void deleteItem(
            @PathVariable("itemId") Long itemId) {
        itemService.deleteItem(itemId);
    }


}