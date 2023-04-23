
package com.toolshare.toolshare.security;

import org.springframework.security.core.Authentication;
import javax.servlet.http.HttpServletRequest;

/**
 * Provides methods for generating and validating JSON Web Tokens (JWTs).
 */
public interface JwtProvider {

    /**
     * Generates a JWT for the specified user.
     *
     * @param auth the authenticated user
     * @return the generated JWT
     */
    String generateToken(UserPrinciple auth);

    /**
     * Extracts the authentication information from the
     * specified HTTP request and returns an
     * Authentication object if the token is valid, or null if it is not.
     *
     * @param request the HTTP request
     * @return an Authentication object if the token is
     * valid, or null if it is not
     */
    Authentication getAuthentication(HttpServletRequest request);

    /**
     * Returns true if the authentication token in the
     * specified HTTP request is valid, or false if it
     * is not or if the token is missing.
     *
     * @param request the HTTP request
     * @return true if the authentication token is
     * valid, or false if it is not or if the token is missing
     */
    boolean isTokenValid(HttpServletRequest request);
}
