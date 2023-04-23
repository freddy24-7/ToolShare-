package com.toolshare.toolshare.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toolshare.toolshare.dto.UserDto;
import com.toolshare.toolshare.model.User;
import com.toolshare.toolshare.security.CustomUserDetailsService;
import com.toolshare.toolshare.security.jwt.JwtProvider;
import com.toolshare.toolshare.service.securityservice.AuthenticationServiceImpl;
import com.toolshare.toolshare.service.securityservice.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Setting up the controller-class that is being tested
@WebMvcTest(AuthenticationController.class)
class AuthenticationControllerTest {

    //Mocking the required services with Mockbean
    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private AuthenticationServiceImpl authenticationService;

    //Mocking these two service below as they are required for the application context
    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private JwtProvider jwtProvider;

    //MockMvc allows the testing of the controller layer without the use of an http-server
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateNewUser() throws Exception {

        //Arrange
        User funnyUser = new User();
        funnyUser.setId(1L);
        funnyUser.setUsername("tom");
        funnyUser.setPassword("Covid2019");
        //Act
        when(userService.saveUser(any(User.class))).thenReturn(funnyUser);
        this.mockMvc.perform(post("http://localhost:8080/api/authentication/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funnyUser)))
                //Assert
                .andExpect(status().isCreated());
        }

    @Test
    void shouldSignInNewUser() throws Exception {

        //Arrange
        User funnyUser = new User();
        funnyUser.setId(1L);
        funnyUser.setUsername("tom");
        funnyUser.setPassword("Covid2019");
        //Act
        when(authenticationService.signInAndReturnJWT(any(User.class))).thenReturn(funnyUser);
        this.mockMvc.perform(post("http://localhost:8080/api/authentication/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funnyUser)))
                //Assert
                .andExpect(status().isOk());
    }
}