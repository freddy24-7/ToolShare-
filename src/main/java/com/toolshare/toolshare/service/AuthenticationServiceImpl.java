
package com.toolshare.toolshare.service;

import com.toolshare.toolshare.model.User;
import com.toolshare.toolshare.security.UserPrinciple;
import com.toolshare.toolshare.security.JwtProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    /**
     * The AuthenticationManager used for authenticating users.
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * The JwtProvider used for generating JSON Web Tokens (JWTs).
     */
    @Autowired
    private JwtProvider jwtProvider;

    /**
     * Obtain the JSON Web Token (JWT) that enables the user to log in.
     *
     * @param signInRequest The user's login credentials.
     * @return The User object associated with the authenticated user.
     */
    @Override
    public User signInAndReturnJWT(final User signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getUsername(),
                                signInRequest.getPassword())
        );
        UserPrinciple userPrinciple = (
                UserPrinciple) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrinciple);
        User signInUser = userPrinciple.getUser();
        signInUser.setToken(jwt);

        return signInUser;
    }
}
