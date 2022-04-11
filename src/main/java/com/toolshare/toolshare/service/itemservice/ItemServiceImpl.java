package com.toolshare.toolshare.service.itemservice;

import com.toolshare.toolshare.model.ShareItem;
import com.toolshare.toolshare.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public ShareItem saveItem(ShareItem shareItem)
    {
        shareItem.setCreateTime(LocalDateTime.now());

        return itemRepository.save(shareItem);
    }

    @Override
    public void deleteItem(Long itemId)
    {
        itemRepository.deleteById(itemId);
    }

    @Override
    public List<ShareItem> findAllItems()
    {
        return itemRepository.findAll();
    }

}





