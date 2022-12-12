package com.toolshare.toolshare.service.itemservice;

import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.model.ShareItem;
import com.toolshare.toolshare.repository.ItemRepository;
import com.toolshare.toolshare.repository.ParticipantRepository;
import com.toolshare.toolshare.service.participantservice.ParticipantServiceImpl;
import com.toolshare.toolshare.service.securityservice.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

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
    void shouldDeleteShareItem() {
        //Arrange
        ShareItem newItem = new ShareItem();
        newItem.setItemId(2L);
        newItem.setItemName("Drill");
        newItem.setDescription("Make holes");
        newItem.setPhotoURL("www.photoMe.com");
        //Act
        when(itemRepository.findById(anyLong())).thenReturn(Optional.of(newItem));
        doNothing().when(itemRepository).delete(any(ShareItem.class));
        underTest.deleteItem(2L);
        //Assert
        verify(itemRepository, times(1)).delete(newItem);
    }

    @Test
    void shouldFindAllItems() {
        //Arrange
        ShareItem newItem = new ShareItem();
        newItem.setItemId(3L);
        newItem.setItemName("Saw");
        newItem.setDescription("Can cut a plank");
        newItem.setPhotoURL("www.saw.com");

        ShareItem anotherItem = new ShareItem();
        anotherItem.setItemId(4L);
        anotherItem.setItemName("Hammer");
        anotherItem.setDescription("Can hit a nail");
        anotherItem.setPhotoURL("www.nail.com");

        List<ShareItem> itemList = new ArrayList<>();
        itemList.add(newItem);
        itemList.add(anotherItem);

        //Act
        when(itemRepository.findAll()).thenReturn(itemList);
        List<ShareItem> shareItems = underTest.findAllItems();
        //Assert
        assertEquals(2, shareItems.size());
        assertNotNull(shareItems);
    }

    @Test
    void shouldObtainShareItemsById() {
        //Arrange
        ShareItem newItem = new ShareItem();
        newItem.setItemId(3L);
        newItem.setItemName("Saw");
        newItem.setDescription("Can cut a plank");
        newItem.setPhotoURL("www.saw.com");
        //Act
        when(itemRepository.findById(anyLong())).thenReturn(Optional.of(newItem));
        ShareItem oneMoreShareItem = underTest.getShareItemsById(3L);
        //Assert
        assertNotNull(oneMoreShareItem);
        assertThat(oneMoreShareItem.getItemId()).isEqualTo(3L);
    }

    @Test
    void shouldThrowExceptionWhenWrongIdCalled() {
        //Arrange
        ShareItem newItem = new ShareItem();
        newItem.setItemId(3L);
        newItem.setItemName("Saw");
        newItem.setDescription("Can cut a plank");
        newItem.setPhotoURL("www.saw.com");
        //Act
        when(itemRepository.findById(3L)).thenReturn(Optional.of(newItem));
        //Assert
        assertThrows(RuntimeException.class, () -> {
            underTest.getShareItemsById(1L);
        });
    }

    @Test
    void shouldUpdateShareItemAndShareToDatabase() {
        //Arrange
        ShareItem newItem = new ShareItem();
        newItem.setItemId(3L);
        newItem.setItemName("Saw");
        newItem.setDescription("Can cut a plank");
        newItem.setPhotoURL("www.saw.com");
        //Act
        when(itemRepository.findById(anyLong())).thenReturn(Optional.of(newItem));
        when(itemRepository.save(any(ShareItem.class))).thenReturn(newItem);
        newItem.setItemName("Topsaw500");
        ShareItem updatedShareitem = underTest.updateShareItem(3L, newItem);
        //Assert
        assertNotNull(updatedShareitem);
        assertEquals("Topsaw500", updatedShareitem.getItemName());

    }
}