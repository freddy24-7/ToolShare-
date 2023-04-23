package com.toolshare.toolshare.dto;

import com.toolshare.toolshare.dto.UserDto;
import com.toolshare.toolshare.model.User;

public class UserUtility {

    public static UserDto convertToDto(User user) {
        UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getRole(), user.getToken());
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole());
        userDto.setToken(user.getToken());
        return userDto;
    }
}