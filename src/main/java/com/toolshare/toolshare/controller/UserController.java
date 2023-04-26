
package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.model.Role;
import com.toolshare.toolshare.model.User;
import com.toolshare.toolshare.security.UserPrinciple;
import com.toolshare.toolshare.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    /**
     * The user service where the business logic takes place.
     */
    private final UserService userService;

    /**
     * Constructs a new UserController with the specified user service.
     *
     * @param userUserService the user service to use
     */
    @Autowired
    public UserController(final UserService userUserService) {
        this.userService = userUserService;
    }

    /**
     * Returns a list of all users.
     *
     * @return a list of all users
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    /**
     * Allows an administrator to change the role of a user in the application.
     *
     * @param userPrinciple the authenticated user principal
     * @param role the new role to assign to the user
     * @return a response entity indicating success or failure of the operation
     */
    @PutMapping("change/{role}")
    public ResponseEntity<Void> changeRole(
            @AuthenticationPrincipal final UserPrinciple userPrinciple,
            @PathVariable final Role role) {
        userService.changeRole(role, userPrinciple.getUsername());
        return ResponseEntity.ok().build();
    }
    /**
     * Deletes a user with the specified ID.
     *
     * <p>
     * This endpoint requires the logged-in user to have the 'ADMIN' role.
     * If the logged-in user
     * is not an admin, a 403 Forbidden error will be returned.
     * </p>
     *
     * @param id The ID of the user to delete.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable final Long id) {
        userService.deleteUser(id);
    }
}
