
package com.toolshare.toolshare.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UsernameNotFoundExceptionCustom extends UsernameNotFoundException {

    /**
     * Constructs a new {@code UsernameNotFoundExceptionCustom}
     * with the specified detail message.
     *
     * @param message the detail message
     */
    public UsernameNotFoundExceptionCustom(final String message) {
        super(message);
    }
}
