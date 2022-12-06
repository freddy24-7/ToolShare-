package com.toolshare.toolshare.service.itemservice;

import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.model.ShareItem;
import com.toolshare.toolshare.repository.ItemRepository;
import com.toolshare.toolshare.repository.ParticipantRepository;
import com.toolshare.toolshare.service.participantservice.ParticipantServiceImpl;
import com.toolshare.toolshare.service.securityservice.UserServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {

    //Mocking the two required repositories for this class
    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ParticipantRepository participantRepository;

    //Injecting test classes
    @InjectMocks
    private ParticipantServiceImpl participantService;

    @InjectMocks
    private ItemServiceImpl underTest;

    //Mocking userService for authentication
    @Mock
    private UserServiceImpl userService;

    @Test
    void shouldCreateAndSaveShareItemToDataBase() throws Exception {

        //Arrange
        Participant funnyParticipant = new Participant();
        funnyParticipant.setId(1L);
        funnyParticipant.setFirstName("tom");
        funnyParticipant.setLastName("hansen");
        funnyParticipant.setPhotoURL("myphoto.com");
        funnyParticipant.setEmail("tom@myphoto.com");
        funnyParticipant.setPostcode("3456HW");
        funnyParticipant.setMobileNumber("0909546543");

        ShareItem newItem = new ShareItem();
        newItem.setItemId(2L);
        newItem.setItemName("Drill");
        newItem.setDescription("Make holes");
        newItem.setPhotoURL("www.photoMe.com");

        //Act
        when(participantRepository.save(any(Participant.class))).thenReturn(funnyParticipant);
        Participant newParticipant = participantService.saveParticipant(funnyParticipant);

        participantRepository.findById(newParticipant.getId()).map(participant -> {
                    participant.getItems().add(newItem);
                    itemRepository.save(newItem);
                    return newItem;
                });
        //Assert
        assertNotNull(newParticipant);
        assertNotNull(newItem);
        assertThat(newParticipant.getFirstName()).isEqualTo("tom");
        assertThat(newParticipant.getId()).isEqualTo(1);
        assertThat(newParticipant.getItems().equals(newItem));
        assertThat(newItem.getItemName()).isEqualTo("Drill");
        assertThat(newItem.getItemId()).isEqualTo(2);
    }

    @Test
    @Disabled
    void deleteItem() {
    }

    @Test
    @Disabled
    void findAllItems() {
    }

    @Test
    @Disabled
    void getShareItemsById() {
    }


    @Test
    @Disabled
    void updateShareItem() {
    }
}