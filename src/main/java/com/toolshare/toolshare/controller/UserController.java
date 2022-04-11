package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.model.Role;
import com.toolshare.toolshare.security.UserPrinciple;
import com.toolshare.toolshare.service.securityservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping("change/{role}")//api/user/change/{role}
    public ResponseEntity<?> changeRole(@AuthenticationPrincipal UserPrinciple userPrinciple, @PathVariable Role role) {
        userService.changeRole(role, userPrinciple.getUsername());

        return ResponseEntity.ok(true);
    }
}