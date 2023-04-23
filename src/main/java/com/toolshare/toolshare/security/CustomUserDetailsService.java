
package com.toolshare.toolshare.security;

import com.toolshare.toolshare.model.User;
import com.toolshare.toolshare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Custom User Details Service.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * The UserService dependency to be injected by Spring.
     */
    @Autowired
    private UserService userService;

    /**
     * Loads the user by username.
     *
     * @param username The username of the user
     * @return The UserDetails object representing the user
     * @throws UsernameNotFoundException Thrown when the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        Set<GrantedAuthority> authorities =
                Set.of(SecurityUtils.convertToAuthority(user.getRole().name()));

        return UserPrinciple.builder()
                .user(user)
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
