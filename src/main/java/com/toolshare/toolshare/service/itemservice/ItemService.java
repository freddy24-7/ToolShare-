package com.toolshare.toolshare.service.itemservice;

import com.toolshare.toolshare.model.ShareItem;
import java.util.List;

public interface ItemService {

    void deleteItem(Long itemId);

    List<ShareItem> findAllItems();

    ShareItem getShareItemsById (Long itemId);

    ShareItem createShareItem (Long id, ShareItem shareItemAddition);

    ShareItem updateShareItem (long itemId, ShareItem shareItem);




}
