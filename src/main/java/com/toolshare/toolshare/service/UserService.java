
package com.toolshare.toolshare.service;

import com.toolshare.toolshare.model.Role;
import com.toolshare.toolshare.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserService {

        /**
         * Saves a user object to the database.
         *
         * @param user The user object to save.
         * @return The saved user object.
         */
        User saveUser(User user);

        /**
         * Finds a user object by username.
         *
         * @param username The username to search for.
         * @return An Optional containing the user object,
         * or empty if not found.
         */
        Optional<User> findByUsername(String username);

        /**
         * Changes the role of a user.
         *
         * @param newRole The new role to assign.
         * @param username The username of the user to update.
         */
        void changeRole(Role newRole, String username);

        /**
         * Retrieves all user objects from the database.
         *
         * @return A List of all user objects.
         */
        List<User> findAllUsers();

        /**
         * Retrieves the logged in user.
         *
         * @return The logged in user.
         */
        User getLoggedInUser();


        @Transactional
        void deleteUser(Long userId);
}
