
package com.toolshare.toolshare.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A filter that extracts JWTs from incoming requests and
 * sets the authentication information for
 * the request, if the token is valid.
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    /**
     * The provider used for generating and validating JSON Web Tokens (JWTs).
     */
    @Autowired
    private JwtProvider jwtProvider;

    /**
     * Extracts the authentication information from the specified
     * HTTP request and sets it for the request,
     * if the token is valid.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @param filterChain the filter chain
     * @throws ServletException if the request cannot be handled
     * @throws IOException if an I/O error occurs while handling the request
     */
    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain)
            throws ServletException, IOException {
        Authentication authentication = jwtProvider.getAuthentication(request);
        if (authentication != null && jwtProvider.isTokenValid(request)) {
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
