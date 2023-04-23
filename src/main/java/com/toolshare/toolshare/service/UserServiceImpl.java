
package com.toolshare.toolshare.service;

import com.toolshare.toolshare.exception.UsernameNotFoundExceptionCustom;
import com.toolshare.toolshare.model.Role;
import com.toolshare.toolshare.model.User;
import com.toolshare.toolshare.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    /**
     * The repository for user objects.
     */
    private final UserRepository userRepository;

    /**
     * The encoder for password hashing.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs a new UserServiceImpl with the given dependencies.
     *
     * @param userUserRepository The repository for user objects.
     * @param userPasswordEncoder The encoder for password hashing.
     */
    public UserServiceImpl(final UserRepository userUserRepository,
                           final PasswordEncoder userPasswordEncoder) {
        this.userRepository = userUserRepository;
        this.passwordEncoder = userPasswordEncoder;
    }

    /**
     * Saves a user object to the database.
     * Encrypts the password using the password encoder,
     * assigns the role to USER, and sets the "create time".
     *
     * @param user The user object to save.
     * @return The saved user object.
     */
    @Override
    public User saveUser(final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setCreateTime(Instant.now());
        return userRepository.save(user);
    }

    /**
     * Finds a user object by username.
     *
     * @param username The username to search for.
     * @return An Optional containing the user object, or empty if not found.
     */
    @Override
    public Optional<User> findByUsername(final String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Changes the role of a user.
     *
     * @param newRole The new role to assign.
     * @param username The username of the user to update.
     */
    @Override
    @Transactional
    public void changeRole(final Role newRole, final String username) {
        userRepository.updateUserRole(username, newRole);
    }

    /**
     * Retrieves all user objects from the database.
     *
     * @return A List of all user objects.
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves the logged-in user.
     *
     * @return The logged-in user.
     * @throws UsernameNotFoundExceptionCustom if the user is not found.
     */
    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundExceptionCustom(
                                "Gebruiker niet gevonden"));
    }
}



