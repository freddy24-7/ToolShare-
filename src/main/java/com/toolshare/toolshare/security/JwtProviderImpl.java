
package com.toolshare.toolshare.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Provides methods for generating and validating JSON Web Tokens (JWTs).
 */
@Component
public class JwtProviderImpl implements JwtProvider {

    /**
     * The secret key used for signing and verifying JWTs.
     */
    @Value("${app.jwt.secret}")
    private String jwtSecret;

    /**
     * The expiration time of JWTs in milliseconds.
     */
    @Value("${app.jwt.expiration-in-ms}")
    private Long jwtExpirationInMs;

    /**
     * Generates a JWT for the specified user.
     *
     * @param auth the authenticated user
     * @return the generated JWT
     */
    @Override
    public String generateToken(final UserPrinciple auth) {
        String authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        Key key = Keys.hmacShaKeyFor(jwtSecret
                .getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(auth.getUsername())
                .claim("roles", authorities)
                .claim("userId", auth.getId())
                .setExpiration(new Date(
                        System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Extracts the authentication information from the
     * specified HTTP request and returns an
     * Authentication object if the token is valid, or null if it is not.
     *
     * @param request the HTTP request
     * @return an Authentication object if the token is
     * alid, or null if it is not
     */
    @Override
    public Authentication getAuthentication(final HttpServletRequest request) {
        Claims claims = extractClaims(request);
        if (claims == null) {
            return null;
        }
        String username = claims.getSubject();
        Long userId = claims.get("userId", Long.class);
        Set<GrantedAuthority> authorities = Arrays.stream(
                claims.get("roles").toString().split(","))
                .map(SecurityUtils::convertToAuthority)
                .collect(Collectors.toSet());
        UserDetails userDetails = UserPrinciple.builder()
                .username(username)
                .authorities(authorities)
                .id(userId)
                .build();
        if (username == null) {
            return null;
        }
        return new UsernamePasswordAuthenticationToken(
                userDetails, null, authorities);
    }

    /**
     * Returns true if the authentication token in the specified
     * HTTP request is valid, or false if it
     * is not or if the token is missing.
     *
     * @param request the HTTP request
     * @return true if the authentication token is
     * valid, or false if it is not or if the token is missing
     */
    @Override
    public boolean isTokenValid(final HttpServletRequest request) {
        Claims claims = extractClaims(request);
        if (claims == null) {
            return false;
        }
        if (claims.getExpiration().before(new Date())) {
            return false;
        }
        return true;
    }

    /**
     * Extracts the claims from the authentication token in the
     * specified HTTP request, or null if
     * the token is missing or invalid.
     *
     * @param request the HTTP request
     * @return the claims from the authentication
     * token, or null if the token is missing or invalid
     */
    private Claims extractClaims(final HttpServletRequest request) {
        String token = SecurityUtils.extractAuthTokenFromRequest(request);
        if (token == null) {
            return null;
        }
        Key key = Keys.hmacShaKeyFor(jwtSecret
                .getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
