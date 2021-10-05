package com.epam.example.shoppinglist.web.controller;

import com.epam.example.shoppinglist.web.domain.UserView;
import com.epam.example.shoppinglist.error.UserAlreadyExistException;
import com.epam.example.shoppinglist.error.UserNotFoundException;
import com.epam.example.shoppinglist.web.service.UserServiceInterface;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller that handles User related operations.
 */
@RestController
public class UserController {

    private static final String GET_USER_MAPPING = "/user/{id}";
    private static final String GET_ALL_USER_MAPPING = "/users";
    private static final String ADD_USER = "/user/add";


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
    public UserView getUser(@PathVariable long id){
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
     * If the users id already in use will throw {@link UserAlreadyExistException}.
     *
     * @param user A {@link UserView} that will be added to the DB.
     */
    @PostMapping(path = ADD_USER)
    public void addUser(@RequestBody UserView user){
         userService.addUser(user);
    }

    //TODO Delete endpoint
}