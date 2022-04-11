package com.toolshare.toolshare.service.securityservice;

import com.toolshare.toolshare.model.Role;
import com.toolshare.toolshare.model.User;

import java.util.Optional;

public interface UserService {
        User saveUser(User user);

        Optional<User> findByUsername(String username);

        void changeRole(Role newRole, String username);

}

