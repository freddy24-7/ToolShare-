package com.toolshare.toolshare.security;

import com.toolshare.toolshare.model.User;
import com.toolshare.toolshare.service.securityservice.UserServiceImpl;
import com.toolshare.toolshare.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

//This class uses spring security and defines the user

@Service
public class CustomUserDetailsService implements UserDetailsService {

    //instantiating UserService, from where we will obtain the user
    @Autowired
    private UserServiceImpl userService;

    //Defining authorities and building the user using the UserPrinciple class
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(user.getRole().name()));
        return UserPrinciple.builder()
                .user(user)
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
