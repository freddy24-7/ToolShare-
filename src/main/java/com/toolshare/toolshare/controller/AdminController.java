package com.toolshare.toolshare.controller;


import com.toolshare.toolshare.model.User;
import com.toolshare.toolshare.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin")
public class AdminController
{
    @Autowired
    private IUserService userService;

    //GET http://localhost:8080/api/admin/all
    //Can only be reached by ADMIN
    @GetMapping("all")
    public ResponseEntity<?> getAllUsers()
    {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    //DELETE http://localhost:8080/api/admin/{userId}
    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId)
    {
        User user = userService.deleteUser(userId);
        return ResponseEntity.ok(user);
    }
}
