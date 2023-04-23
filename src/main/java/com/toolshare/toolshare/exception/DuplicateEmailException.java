
package com.toolshare.toolshare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception that indicates a duplicate email
 * was found when trying to create a new user.
 * This exception is mapped to an HTTP 409 response.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEmailException extends RuntimeException {

    /**
     * Creates a new DuplicateEmailException with the specified message.
     *
     * @param msg the detail message
     */
    public DuplicateEmailException(final String msg) {
        super(msg);
    }
}
