package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.model.ShareItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository <ShareItem, Long> {

    //This query is not built in so have to create a custom query

    @Query("select " +
            "sha.itemName as name, par.firstName as firstName " +
            "from ShareItem sha left join Participant par on sha.participant.id = par.id " +
            "where sha.participant.id = :id")
    List<ShareItem> findItemsOfParticipant(@Param("id") Long id);

}
