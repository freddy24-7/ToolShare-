package com.toolshare.toolshare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//This is an exception class to indicate that a user has not been found. This can for example happen if a user
//tries to login with a non-existing username

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String msg) {

        super(msg);
    }
}
