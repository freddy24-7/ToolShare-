//package com.toolshare.toolshare.controller;
//
//import com.toolshare.toolshare.model.Role;
//import com.toolshare.toolshare.model.User;
//import com.toolshare.toolshare.security.CustomUserDetailsService;
//import com.toolshare.toolshare.security.jwt.JwtProviderImpl;
//import com.toolshare.toolshare.service.securityservice.UserServiceImpl;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
////Setting up the controller-class that is being tested
//@WebMvcTest(UserController.class)
////Autoconfiguring to MockMvc with addFilters = false to prevent 403-error
//@AutoConfigureMockMvc(addFilters = false)
//class UserControllerTest {
//
//    @MockBean
//    private UserServiceImpl userService;
//
//    //Mocking these two services below as they are required for the application context
//    @MockBean
//    private CustomUserDetailsService customUserDetailsService;
//
//    @MockBean
//    private JwtProviderImpl jwtProvider;
//
//    //MockMvc allows the testing of the controller layer without the use of an http-server
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void getAllUsers() throws Exception {
//
//        User funnyUser = new User();
//        funnyUser.setUsername("peter");
//        funnyUser.setPassword("Covid2019");
//        funnyUser.setRole(Role.USER);
//        funnyUser.setCreateTime(Instant.now());
//
//        User topUser = new User();
//        topUser.setUsername("peter");
//        topUser.setPassword("Covid2019");
//        topUser.setRole(Role.USER);
//        topUser.setCreateTime(LocalDateTime.now());
//
//        List<User> userList = new ArrayList<>();
//        userList.add(funnyUser);
//        userList.add(topUser);
//
//        when(userService.findAllUsers()).thenReturn(userList);
//
//        this.mockMvc.perform(get("http://localhost:8080/api/user"))
//                .andExpect(status().isOk());
//
//    }
//
//    @Test
//    @Disabled
//    void changeRole() {
//    }
//}