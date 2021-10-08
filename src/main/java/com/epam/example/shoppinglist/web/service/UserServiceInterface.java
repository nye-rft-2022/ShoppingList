package com.epam.example.shoppinglist.web.service;

import com.epam.example.shoppinglist.data.domain.UserEntity;
import com.epam.example.shoppinglist.web.domain.CreateUserRequest;
import com.epam.example.shoppinglist.web.domain.UserView;

import java.util.List;

/**
 * Service object to handle {@link UserEntity} related logic.
 */
public interface UserServiceInterface {

    /**
     * Returns a {@link UserView} with the given id.
     * The user lists are sorted by name.
     *
     * @param id The id of the user, cannot be null.
     * @return A {@link UserView} with the given id or a {@link com.epam.example.shoppinglist.error.UserNotFoundException} if the given id is null or non-existing.
     */
    UserView getUserById(Long id);

    /**
     * Returns all users in an ordered list.
     * The list is in alphabetical order.
     *
     * @return a {@link List} of {@link UserView}
     */
    List<UserView> getAllUser();

    /**
     * Creates a new user and saves it.
     *
     * @param request The User that should be saved.
     */
    void addUser(CreateUserRequest request);

    /**
     * Deletes a user with the given id if its exists.
     *
     * @param id The id of the user.
     */
    void deleteUserById(Long id);
}
