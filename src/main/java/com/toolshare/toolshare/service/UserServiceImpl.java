package com.toolshare.toolshare.service;

import com.toolshare.toolshare.model.User;
import com.toolshare.toolshare.model.Role;
import com.toolshare.toolshare.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setCreateTime(LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void changeRole(Role newRole, String username)
    {
        userRepository.updateUserRole(username, newRole);
    }

    @Override
    public void updateUser(Long id, String username, String email, String password, String mobileNumber) {

    }

    //TODO move to separate class (not implemented here)
    @Transactional
    public void updateUser(Long userId,
                           String username,
                           String email,
                           String password) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                        "gebruiker met id " + userId + " bestaat niet."));

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(user.getEmail(), email)) {
            Optional<User> userOptional = userRepository
                    .findUserByEmail(email);
            if (userOptional.isPresent()) {
                throw new IllegalStateException("email bestaat al");
            }
            user.setEmail(email);

        }
        if (username != null &&
                username.length() > 0 &&
                !Objects.equals(user.getUsername(), username)) {
            Optional<User> userOptional = userRepository
                    .findByUsername(username);
            if (userOptional.isPresent()) {
                throw new IllegalStateException("email bestaat al");
            }
            user.setUsername(username);

        }
        if (password != null &&
                password.length() > 0 &&
                !Objects.equals(user.getPassword(), password)) {
            user.setPassword(password);
        }

    }

}
