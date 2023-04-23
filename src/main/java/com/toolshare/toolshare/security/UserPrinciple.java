
package com.toolshare.toolshare.security;

import com.toolshare.toolshare.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPrinciple implements UserDetails {

    /**

     The ID of the user.
     */
    private Long id;
    /**

     The username of the user.
     */
    private String username;
    /**

     The password of the user (transient field).
     */
    private String password;
    /**

     The User object of the user.
     */
    private User user;
    /**

     The Set of GrantedAuthority objects assigned to the user.
     */
    private Set<GrantedAuthority> authorities;
    /**

     Returns the authorities assigned to the user.
     @return The Set of authorities assigned to the user.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    /**

     Returns the password of the user.
     @return The password of the user.
     */
    @Override
    public String getPassword() {
        return password;
    }
    /**

     Returns the username of the user.
     @return The username of the user.
     */
    @Override
    public String getUsername() {
        return username;
    }
    /**

     Returns true if the user's account is not expired, false otherwise.
     @return A boolean value indicating whether the user's account
     is not expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /**

     Returns true if the user's account is not locked, false otherwise.
     @return A boolean value indicating whether the user's
     account is not locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /**

     Returns true if the user's credentials are not expired, false otherwise.
     @return A boolean value indicating whether the user's
     credentials are not expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**

     Returns true if the user is enabled, false otherwise.
     @return A boolean value indicating whether the user is enabled.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
