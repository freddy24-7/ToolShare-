
package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.dto.UserDto;
import com.toolshare.toolshare.dto.UserUtility;
import com.toolshare.toolshare.model.User;
import com.toolshare.toolshare.service.AuthenticationService;
import com.toolshare.toolshare.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "This API signs up a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "User created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "409",
                    description = "Username already exists"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
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
    @Operation(summary = "This API signs in a new user and returns a JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description =
                            "User signed in and JWT token returned to client",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403",
                    description =
                            "Not authenticated, wrong username or password"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    public ResponseEntity<?> signIn(@RequestBody final User user) {
        User loggedInUser = authenticationService.signInAndReturnJWT(user);
        UserDto userDtoResponse = UserUtility.convertToDto(loggedInUser);
        return new ResponseEntity<>(userDtoResponse, HttpStatus.OK);
    }
}
