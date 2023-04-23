
package com.toolshare.toolshare.service;

import com.toolshare.toolshare.model.User;

public interface AuthenticationService {


    /**
     * Authenticate the user with the provided credentials and return a JWT.
     *
     * @param signInRequest The user's login credentials.
     * @return The User object associated with the
     * authenticated user, including a JWT.
     */
    User signInAndReturnJWT(User signInRequest);
}
