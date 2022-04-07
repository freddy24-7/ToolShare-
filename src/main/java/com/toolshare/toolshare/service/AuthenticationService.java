package com.toolshare.toolshare.service;

import com.toolshare.toolshare.model.User;

public interface AuthenticationService {
    User signInAndReturnJWT(User signInRequest);
}