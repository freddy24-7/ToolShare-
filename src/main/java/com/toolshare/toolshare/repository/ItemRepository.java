
package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.model.ShareItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides methods for accessing and manipulating
 * share item data in the database.
 */
@Repository
public interface ItemRepository extends JpaRepository<ShareItem, Long> {

}
