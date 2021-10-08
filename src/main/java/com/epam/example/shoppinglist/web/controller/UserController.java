package com.epam.example.shoppinglist.web.controller;

import com.epam.example.shoppinglist.web.domain.CreateUserRequest;
import com.epam.example.shoppinglist.web.domain.UserView;
import com.epam.example.shoppinglist.error.EmailAlreadyInUseException;
import com.epam.example.shoppinglist.error.UserNotFoundException;
import com.epam.example.shoppinglist.web.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * REST Controller that handles User related operations.
 */
@RestController
public class UserController {

    private static final String GET_USER_MAPPING = "/user/{id}";
    private static final String GET_ALL_USER_MAPPING = "/user/users";
    private static final String ADD_USER = "/user/add";
    private static final String DELETE_USER_MAPPING = "/user/delete/{id}";

    private UserServiceInterface userService;

    @Autowired
    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    /**
     * Returns a {@link UserView} as JSON.
     *
     * @param id The id of the user.
     * @return A {@link UserView} with the given id or {@link UserNotFoundException} if no user with the given id exists.
     */
    @GetMapping(path = GET_USER_MAPPING)
    public UserView getUser(@PathVariable @NotNull Long id){
        return userService.getUserById(id);
    }

    /**
     * Returns all users as JSON.
     *
     * @return A {@link List} of {@link UserView}.
     */
    @GetMapping(path = GET_ALL_USER_MAPPING)
    public List<UserView> getAllUser(){
        return userService.getAllUser();
    }


    /**
     * Adds a user to the DB.
     * If the users email already in use will throw {@link EmailAlreadyInUseException}.
     *
     * @param request A {@link CreateUserRequest} that will be added to the DB.
     */
    @PostMapping(path = ADD_USER)
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@Valid @RequestBody CreateUserRequest request){
         userService.addUser(request);
    }

    /**
     * Deletes user from the DB.
     *
     * @param id The id of the user.
     */
    @DeleteMapping(DELETE_USER_MAPPING)
    public void deleteUser(@PathVariable @NotNull Long id){
        userService.deleteUserById(id);
    }
}