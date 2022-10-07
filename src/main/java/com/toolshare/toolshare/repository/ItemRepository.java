package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.model.ShareItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository <ShareItem, Long> {

}
