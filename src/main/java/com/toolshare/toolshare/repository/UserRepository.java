
package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.model.User;
import com.toolshare.toolshare.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

/**
 * Provides methods for accessing and manipulating user data in the database.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user with the specified username, if it exists.
     *
     * @param username the username to search for
     * @return an Optional containing the user with the
     * specified username, or empty if it does not exist
     */
    Optional<User> findByUsername(String username);

    /**
     * Updates the role of a user with the specified username.
     *
     * @param username the username of the user to update
     * @param role the new role of the user
     */
    @Modifying
    @Query("update User set role = :role where username = :username")
    void updateUserRole(@Param("username") String username,
                        @Param("role")Role role);
}
