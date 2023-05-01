package com.toolshare.toolshare.service.securityservice;

import com.toolshare.toolshare.model.User;
import com.toolshare.toolshare.repository.UserRepository;
import com.toolshare.toolshare.service.UserServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    //Mocking repository
    @Mock
    private UserRepository userRepository;

    //Mocking the passwordEncoder, required for log-in
    @Mock
    private PasswordEncoder passwordEncoder;

    //Creating an instance of the userService
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldSaveUser() {
        //Arrange
        User funnyUser = new User();
        funnyUser.setUsername("tom");
        funnyUser.setPassword("Covid2019");
        //Act
        when(userRepository.save(any(User.class))).thenReturn(funnyUser);
        User newUser = userService.saveUser(funnyUser);
        //Assert
        assertNotNull(newUser);
        assertThat(newUser.getUsername()).isEqualTo("tom");
    }

    @Test
    @Disabled
    void findByUsername() {
    }

    @Test
    @Disabled
    void changeRole() {
    }

    @Test
    void shouldReturnListOfAllUsers() {
        //Arrange
        User funnyUser = new User();
        funnyUser.setUsername("tom");
        funnyUser.setPassword("Covid2019");

        User happyUser = new User();
        happyUser.setUsername("Irene");
        happyUser.setPassword("WorldCup2022");

        List<User> userList = new ArrayList<>();
        userList.add(funnyUser);
        userList.add(happyUser);

        //Act
        when(userRepository.findAll()).thenReturn(userList);
        List<User> users = userService.findAllUsers();
        //Assert
        assertEquals(2, users.size());
        assertNotNull(users);
    }

    @Test

    void getLoggedInUser() {

    }
}