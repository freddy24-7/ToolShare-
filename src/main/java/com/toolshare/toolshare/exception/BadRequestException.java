package com.toolshare.toolshare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    /**
     * Constructs a new {@code BadRequestException} with the
     * specified detail message.
     *
     * @param message the detail message
     */
    public BadRequestException(final String message) {
        super(message);
    }
}
