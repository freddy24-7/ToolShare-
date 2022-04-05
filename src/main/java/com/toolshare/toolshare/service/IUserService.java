package com.toolshare.toolshare.service;

import com.toolshare.toolshare.model.Role;
import com.toolshare.toolshare.model.User;

import java.util.List;

public interface IUserService {

        User saveUser(User user);

        User changeRole(Role newRole, String username);

        User findByUsername(String username);

        User deleteUser(Long userId);

        List<User> findAllUsers();

        void updateUser(Long userId, String username, String email, String password, String firstName, String lastName, String mobileNumber);
}

