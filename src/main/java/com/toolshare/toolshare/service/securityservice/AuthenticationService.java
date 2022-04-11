package com.toolshare.toolshare.service.securityservice;

import com.toolshare.toolshare.model.User;

public interface AuthenticationService {
    User signInAndReturnJWT(User signInRequest);
}