package com.toolshare.toolshare.service;

import com.toolshare.toolshare.model.User;
import com.toolshare.toolshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
//    TODO: Add admin role functionality
//    TODO: Add a new separate Spring Security Class

    public User findByLastName(String lastname) {
        return userRepository.findByLastName(lastname).orElseThrow(() -> new RuntimeException());
    }
    public User deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException());
        userRepository.delete(user);
        return user;
    }



}
