
package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.dto.UserDto;
import com.toolshare.toolshare.dto.UserUtility;
import com.toolshare.toolshare.model.User;
import com.toolshare.toolshare.service.securityservice.AuthenticationService;
import com.toolshare.toolshare.service.securityservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> signUp(@RequestBody User userDto) {
        if (userService.findByUsername(userDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        User savedUser = userService.saveUser(userDto);
        UserDto userDtoResponse = UserUtility.convertToDto(savedUser);
        return new ResponseEntity<>(userDtoResponse, HttpStatus.CREATED);
    }

    //Here we are authenticating a registered user trying to log in, and returning a JWT token
    //token is valid until the user logs out (specified under application.properties)
    @PostMapping("sign-in")//api/authentication/sign-in
    public ResponseEntity<?> signIn(@RequestBody User user) {
        User loggedInUser = authenticationService.signInAndReturnJWT(user);
        UserDto userDtoResponse = UserUtility.convertToDto(loggedInUser);
        return new ResponseEntity<>(userDtoResponse, HttpStatus.OK);
    }


}