package com.toolshare.toolshare.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.model.ShareItem;
import com.toolshare.toolshare.security.CustomUserDetailsService;
import com.toolshare.toolshare.security.JwtProviderImpl;
import com.toolshare.toolshare.service.ItemServiceImpl;
import com.toolshare.toolshare.service.ParticipantServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Setting up the controller-class that is being tested
@WebMvcTest(ItemController.class)
//Autoconfiguring to MockMvc with addFilters = false to prevent 403-error
@AutoConfigureMockMvc(addFilters = false)
class ItemControllerTest {

    //Mocking the required service with Mockbean
    @MockBean
    private ItemServiceImpl itemService;

    @MockBean
    private ParticipantServiceImpl participantService;

    //Mocking these two services below as they are required for the application context
    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private JwtProviderImpl jwtProvider;

    //MockMvc allows the testing of the controller layer without the use of an http-server
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllItems() throws Exception {

        ShareItem newItem = new ShareItem();
        newItem.setItemId(3L);
        newItem.setItemName("Saw");
        newItem.setDescription("Can cut a plank");
        newItem.setPhotoURL("www.saw.com");

        ShareItem anotherItem = new ShareItem();
        anotherItem.setItemId(4L);
        anotherItem.setItemName("Hammer");
        anotherItem.setDescription("Can hit a nail");
        anotherItem.setPhotoURL("www.hammer.com");

        List<ShareItem> shareItemList = new ArrayList<>();
        shareItemList.add(newItem);
        shareItemList.add(anotherItem);

        when(itemService.findAllItems()).thenReturn(shareItemList);

        this.mockMvc.perform(get("http://localhost:8080/api/items"))
                .andExpect(status().isOk());


    }

    @Test
    void shouldGetShareItemById() throws Exception {

        ShareItem newItem = new ShareItem();
        newItem.setItemId(3L);
        newItem.setItemName("Saw");
        newItem.setDescription("Can cut a plank");
        newItem.setPhotoURL("www.saw.com");
        //Act & stubbing the method
        when(itemService.getShareItemsById(anyLong())).thenReturn(newItem);
        this.mockMvc.perform(get("http://localhost:8080/api/items/{itemId}", 3L))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @Disabled
    void getAllItemsByParticipantId() {
    }

    @Test
    void createShareItem() throws Exception {

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
        newItem.setItemId(3L);
        newItem.setItemName("Saw");
        newItem.setDescription("Can cut a plank");
        newItem.setPhotoURL("www.saw.com");
        //Act
        when(itemService.createShareItem(1L, (ShareItem.class).newInstance())).thenReturn(newItem);

        this.mockMvc.perform(post("http://localhost:8080/api/participants/items/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newItem)))
                //Assert
                .andExpect(status().isOk());
    }


    @Test
    void updateShareItem() throws Exception {

        //Assert
        ShareItem newItem = new ShareItem();
        newItem.setItemId(3L);
        newItem.setItemName("Saw");
        newItem.setDescription("Can cut a plank");
        newItem.setPhotoURL("www.saw.com");
        //Act - and stub method
        when(itemService.updateShareItem(3L, newItem)).thenReturn(newItem);
        this.mockMvc.perform(put("http://localhost:8080/api/items/{itemId}", 3L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newItem)))
                //Assert
                .andExpect(status().isOk());
    }

    @Test
    void deleteItem() throws Exception {

        ShareItem newItem = new ShareItem();
        newItem.setItemId(3L);
        newItem.setItemName("Saw");
        newItem.setDescription("Can cut a plank");
        newItem.setPhotoURL("www.saw.com");

        //Act
        doNothing().when(itemService).deleteItem(anyLong());
        //Assert
        this.mockMvc.perform(delete("http://localhost:8080/api/items/{itemId}", 3L))
                .andExpect(status().isOk());

    }

    @Test
    @Disabled
    void deleteAllItemsOfParticipant() {
    }
}