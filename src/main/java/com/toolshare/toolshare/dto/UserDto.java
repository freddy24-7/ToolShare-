package com.toolshare.toolshare.dto;

import com.toolshare.toolshare.model.Role;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String username;
    private Role role;

    private String token;

    public UserDto(Long id, String username, Role role, String token) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.token = token;
    }
}