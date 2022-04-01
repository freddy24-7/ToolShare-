package com.toolshare.toolshare.service;


import com.toolshare.toolshare.exception.BadRequestException;
import com.toolshare.toolshare.exception.UserNotFoundException;
import com.toolshare.toolshare.model.User;
import com.toolshare.toolshare.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Boolean existsEmail = userRepository
                .selectExistsEmail(user.getEmail());
        if (existsEmail) {
            throw new BadRequestException(
                    "Email " + user.getEmail() + " bestaat al");
        }
        return userRepository.save(user);
    }
//    TODO: Add admin role functionality
//    TODO: Add a new separate Spring Security Class
//    TODO: Add code for bad-request and user not found exception
//    TODO: Add put method for updating user info - NOW COMPLETED

    public User findByLastName(String lastname) {
        return userRepository.findByLastName(lastname).orElse(null);
    }

    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(
                    "Gebruiker met id " + userId + " bestaat niet");
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId,
                           String email,
                           String password,
                           String firstName,
                           String lastName,
                           String mobileNumber) {
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
        if (password != null &&
                password.length() > 0 &&
                !Objects.equals(user.getPassword(), password)) {
            user.setPassword(password);
        }
        if (firstName != null &&
                firstName.length() > 0 &&
                !Objects.equals(user.getFirstName(), firstName)) {
            user.setFirstName(firstName);
        }
        if (lastName != null &&
                lastName.length() > 0 &&
                !Objects.equals(user.getLastName(), lastName)) {
            user.setLastName(lastName);
        }
        if (mobileNumber != null &&
                mobileNumber.length() > 0 &&
                !Objects.equals(user.getMobileNumber(), mobileNumber)) {
            user.setMobileNumber(mobileNumber);
        }

    }


}









