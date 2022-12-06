package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.model.ImageFile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//Below annotation required for testing repository
@DataJpaTest
class ImageFileRepositoryTest {

    @Autowired
    private ImageFileRepository underTest;

    @Test
    void itShouldSaveDataToDatabase() {
        //Assert
        ImageFile niceImage = new ImageFile();
        niceImage.setFileName("image.jpeg");
        niceImage.setFileType("image/jpeg");
        //Act
        ImageFile newImage = underTest.save(niceImage);
        //Assert
        assertNotNull(newImage);
        assertThat(newImage.getId()).isNotEqualTo(null);

    }
}