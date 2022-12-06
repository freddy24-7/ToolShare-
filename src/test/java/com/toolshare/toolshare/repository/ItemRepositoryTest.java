package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.model.ShareItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//Below annotation required for testing repository
@DataJpaTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository underTest;

    @Test
    void itShouldSaveDataToDatabase() {
        //Assert
        ShareItem shinyItem = new ShareItem();
        shinyItem.setItemName("lawnmover");
        shinyItem.setDescription("handy if you want to cut the grass");
        //Act
        ShareItem newShareItem = underTest.save(shinyItem);
        //Assert
        assertNotNull(newShareItem);
        assertThat(newShareItem.getItemId().equals(1L));

    }

}