package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.model.User;
import com.toolshare.toolshare.service.securityservice.AuthenticationService;
import com.toolshare.toolshare.service.securityservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {

    //Autowiring the service classes where the business logic takes place
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    //Post mapping for sign-up. Here we check that user name is not already taken,
    //and return an error to the frontend if that is the case
    @PostMapping("sign-up")
    public ResponseEntity<?> signUp(@RequestBody User user)
    {
        if (userService.findByUsername(user.getUsername()).isPresent())
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    //Here we are authenticating a registered user trying to log in, and returning a JWT token
    //token is valid until the user logs out - or to a max of 24 hours (specified under application.properties)
    @PostMapping("sign-in")//api/authentication/sign-in
    public ResponseEntity<?> signIn(@RequestBody User user)
    {
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }


}