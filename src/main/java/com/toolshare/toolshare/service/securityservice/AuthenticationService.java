package com.toolshare.toolshare.service.securityservice;

import com.toolshare.toolshare.model.User;

public interface AuthenticationService {

    //Interface for handling JWT-allocation

    User signInAndReturnJWT(User signInRequest);
}