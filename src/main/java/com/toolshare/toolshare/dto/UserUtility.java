
package com.toolshare.toolshare.dto;

import com.toolshare.toolshare.model.User;

/**
 * A utility class for converting User objects to UserDto objects.
 */
public final class UserUtility {

    private UserUtility() {
        // private constructor to prevent instantiation
    }

    /**
     * Converts the specified User object to a UserDto object.
     *
     * @param user the User object to convert
     * @return the converted UserDto object
     */
    public static UserDto convertToDto(final User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.getToken());
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole());
        userDto.setToken(user.getToken());
        return userDto;
    }
}

