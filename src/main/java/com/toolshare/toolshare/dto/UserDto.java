
package com.toolshare.toolshare.dto;

import com.toolshare.toolshare.model.Role;
import lombok.Data;

/**
 * A data transfer object representing a user in the system.
 */
@Data
public class UserDto {

    /** The user's ID. */
    private Long id;

    /** The user's username. */
    private String username;

    /** The user's role. */
    private Role role;

    /** The authentication token for the user. */
    private String token;

    /**
     * Creates a new UserDto with the specified data.
     *
     * @param userId the user's ID
     * @param userUsername the user's username
     * @param userRole the user's role
     * @param userToken the authentication token for the user
     */
    public UserDto(final Long userId,
                   final String userUsername,
                   final Role userRole,
                   final String userToken) {
        this.id = userId;
        this.username = userUsername;
        this.role = userRole;
        this.token = userToken;
    }
}
