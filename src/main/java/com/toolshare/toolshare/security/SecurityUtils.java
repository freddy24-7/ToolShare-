
package com.toolshare.toolshare.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public final class SecurityUtils {

    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * @throws AssertionError if this constructor is
     * called from within the class
     */
    private SecurityUtils() {
        throw new AssertionError();
    }

    /**
     * The prefix used for role names in the application.
     */
    public static final String ROLE_PREFIX = "ROLE_";

    /**
     * The HTTP authorization header name.
     */
    public static final String AUTH_HEADER = "Authorization";

    /**
     * The authentication token type.
     */
    public static final String AUTH_TOKEN_TYPE = "Bearer";

    /**
     * The authentication token prefix, consisting
     * of the token type and a space.
     */
    public static final String AUTH_TOKEN_PREFIX = AUTH_TOKEN_TYPE + " ";

    /**
     * Converts a role to a SimpleGrantedAuthority
     * object with the appropriate role prefix.
     *
     * @param role the role to convert
     * @return a SimpleGrantedAuthority object with the appropriate role prefix
     */
    public static SimpleGrantedAuthority convertToAuthority(final String role) {
        String formattedRole = role.startsWith(ROLE_PREFIX)
                ? role : ROLE_PREFIX + role;
        return new SimpleGrantedAuthority(formattedRole);
    }

    /**
     * Extracts the authentication token from the
     * authorization header of an HTTP request.
     *
     * @param request the HTTP request
     * @return the authentication token, or null if
     * the header is invalid or missing
     */
    public static String extractAuthTokenFromRequest(
            final HttpServletRequest request) {
        String authHeader = request.getHeader(AUTH_HEADER);
        if (StringUtils.hasLength(authHeader) && authHeader.startsWith(
                AUTH_TOKEN_PREFIX)) {
            return authHeader.substring(AUTH_TOKEN_PREFIX.length());
        }
        return null;
    }
}

