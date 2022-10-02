package com.toolshare.toolshare.service.itemservice;

import com.toolshare.toolshare.model.ShareItem;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemService {

    ShareItem saveItem(ShareItem shareItem);

    void deleteItem(Long itemId);

    List<ShareItem> findAllItems();

    List<ShareItem> findItemsOfParticipant(Long id);



}
