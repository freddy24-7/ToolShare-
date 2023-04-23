
package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.dto.UserDto;
import com.toolshare.toolshare.dto.UserUtility;
import com.toolshare.toolshare.model.User;
import com.toolshare.toolshare.service.AuthenticationService;
import com.toolshare.toolshare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {

    /**
     * The authentication service where the business logic
     * for user authentication takes place.
     */
    @Autowired
    private AuthenticationService authenticationService;

    /**
     * The user service where the business logic for
     * user management takes place.
     */
    @Autowired
    private UserService userService;

    /**
     * Handles the sign-up of a new user.
     *
     * @param userDto the user to sign up
     * @return a response entity indicating success or failure
     */
    @PostMapping("sign-up")
    public ResponseEntity<?> signUp(@RequestBody final User userDto) {
        if (userService.findByUsername(userDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        User savedUser = userService.saveUser(userDto);
        UserDto userDtoResponse = UserUtility.convertToDto(savedUser);
        return new ResponseEntity<>(userDtoResponse, HttpStatus.CREATED);
    }

    /**
     * Handles the sign-in of a user.
     *
     * @param user the user to sign in
     * @return a response entity containing the
     * user's information and a JWT token
     */
    @PostMapping("sign-in")
    public ResponseEntity<?> signIn(@RequestBody final User user) {
        User loggedInUser = authenticationService.signInAndReturnJWT(user);
        UserDto userDtoResponse = UserUtility.convertToDto(loggedInUser);
        return new ResponseEntity<>(userDtoResponse, HttpStatus.OK);
    }
}
