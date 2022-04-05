package com.toolshare.toolshare.service;

import com.toolshare.toolshare.exception.BadRequestException;
import com.toolshare.toolshare.exception.UserNotFoundException;
import com.toolshare.toolshare.model.Role;
import com.toolshare.toolshare.model.User;
import com.toolshare.toolshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        Boolean existsEmail = userRepository
                .selectExistsEmail(user.getEmail());
        if (existsEmail) {
            throw new BadRequestException(
                    "Email " + user.getEmail() + " bestaat al");
        }
//        TODO: Add bad request logic for invalid phone input
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.USER);
        return userRepository.save(user);
    }
//    TODO: Add admin role functionality
//    TODO: Add a new separate Spring Security Class
//    TODO: Add code for bad-request and user not found exception - NOW COMPLETED
//    TODO: Add put method for updating user info - NOW COMPLETED

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
   }

   @Override
   public User deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(
                    "Gebruiker met id " + userId + " bestaat niet");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException());
        userRepository.delete(user);
        return user;
    }
    @Override
    public User changeRole(Role newRole, String username)
    {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException());
        user.setRole(newRole);
        return userRepository.save(user);
    }


    @Transactional
    public void updateUser(Long userId,
                           String username,
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
        if (username != null &&
                username.length() > 0 &&
                !Objects.equals(user.getUsername(), username)) {
            Optional<User> userOptional = userRepository
                    .findUserByUsername(username);
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
