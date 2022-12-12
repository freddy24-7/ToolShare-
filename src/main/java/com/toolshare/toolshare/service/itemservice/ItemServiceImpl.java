package com.toolshare.toolshare.service.itemservice;

import com.toolshare.toolshare.exception.ResourceNotFoundException;
import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.model.ShareItem;
import com.toolshare.toolshare.repository.ItemRepository;
import com.toolshare.toolshare.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    //Importing required repositories and instantiating

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    //Below is business logic for deleting, finding, saving and updating ShareItems

    @Override
    public void deleteItem(Long itemId) {
        ShareItem shareItem = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException());
        itemRepository.delete(shareItem);
    }

    @Override
    public List<ShareItem> findAllItems()
    {
        return itemRepository.findAll();
    }

    @Override
    public ShareItem getShareItemsById(Long itemId) {
        ShareItem shareItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("ShareItem niet gevonden met id = " + itemId));
        return shareItem;
    }

    @Override
    public ShareItem createShareItem(Long id, ShareItem shareItemAddition) {
        //mapping through the participantobject to get all existing items before adding one to the
        //items list
        ShareItem shareItem = participantRepository.findById(id).map(participant -> {
            participant.getItems().add(shareItemAddition);
            return itemRepository.save(shareItemAddition);
        }).orElseThrow(() -> new ResourceNotFoundException("Deelnemer niet gevonden met id " + id));
        return shareItem;
    }

    @Override
    public ShareItem updateShareItem(long itemId, ShareItem shareItemEdit) {
        ShareItem itemUpdate = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("ShareItemId " + itemId + "niet gevonden"));
        itemUpdate.setItemName(shareItemEdit.getItemName());
        itemUpdate.setDescription(shareItemEdit.getDescription());
        itemRepository.save(itemUpdate);
        return itemUpdate;
    }

}





