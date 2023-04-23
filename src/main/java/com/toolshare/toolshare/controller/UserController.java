package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.model.Role;
import com.toolshare.toolshare.model.User;
import com.toolshare.toolshare.security.UserPrinciple;
import com.toolshare.toolshare.service.securityservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    //Autowiring the service class where the business logic takes place
    @Autowired
    private UserService userService;

    //GetMapping to return a list of all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    //PutMapping to allow an administrator to change the role of a user in the application
    @PutMapping("change/{role}")
    public ResponseEntity<?> changeRole(@AuthenticationPrincipal UserPrinciple userPrinciple, @PathVariable Role role) {
        userService.changeRole(role, userPrinciple.getUsername());

        return ResponseEntity.ok(true);
    }


}