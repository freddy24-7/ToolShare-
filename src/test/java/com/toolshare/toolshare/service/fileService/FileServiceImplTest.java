package com.toolshare.toolshare.service.fileService;

import com.toolshare.toolshare.model.ImageFile;
import com.toolshare.toolshare.repository.ImageFileRepository;
import com.toolshare.toolshare.service.FileServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileServiceImplTest {

    //Mocking repository
    @Mock
    private ImageFileRepository imageFileRepository;

    //Injecting the test class
    @InjectMocks
    private FileServiceImpl underTest;

    //Mocking a multipartfile, required for saving and uploading
    private
    MockMultipartFile jpgfile = new MockMultipartFile("dataFile", "filename.jpg", "text/plain", "somejpg".getBytes());

    @Test
    void shouldSaveImageFile() throws Exception {
        //Arrange
        ImageFile imageToUpload = new ImageFile();
        imageToUpload.setId("3");
        imageToUpload.setFileName("photoFile");
        imageToUpload.setFileType("jpg");
        //Act
        when(imageFileRepository.save(any((ImageFile.class)))).thenReturn(imageToUpload);
        ImageFile newImage = underTest.saveImageFile(jpgfile);
        //Assert
        assertNotNull(newImage);
        assertThat(newImage.getId()).isEqualTo("3");
    }

    @Test
    void shouldGetImageFileById() throws Exception {
        //Arrange
        ImageFile imageToUpload = new ImageFile();
        imageToUpload.setId("3");
        imageToUpload.setFileName("photoFile");
        imageToUpload.setFileType("jpg");
        //Act
        when(imageFileRepository.findById("3")).thenReturn(Optional.of(imageToUpload));
        ImageFile anotherImage = underTest.getImageFile("3");
        //Assert
        assertNotNull(anotherImage);
        assertThat(anotherImage.getFileName().equals(imageToUpload));
    }
}