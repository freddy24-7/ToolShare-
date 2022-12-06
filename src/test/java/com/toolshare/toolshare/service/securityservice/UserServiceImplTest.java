package com.toolshare.toolshare.service.securityservice;

import com.toolshare.toolshare.model.User;
import com.toolshare.toolshare.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void saveUser() {

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
    @Disabled
    void findAllUsers() {
    }

    @Test

    void getLoggedInUser() {

    }
}