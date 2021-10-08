package com.epam.example.shoppinglist.data.dao;

import com.epam.example.shoppinglist.data.domain.UserEntity;
import com.epam.example.shoppinglist.error.UserNotFoundException;
import com.epam.example.shoppinglist.error.EmailAlreadyInUseException;

import java.util.Collection;

/**
 * DAO interface to access to {@link UserEntity}.
 */
public interface UserDataAccessObjectInterface {
    /**
     * Returns a {@link UserEntity} with the given id.
     *
     * @param userId The id of the user.
     * @return A {@link UserEntity} with the given id or {@link UserNotFoundException} if the given id not exists.
     */
    UserEntity getUserById(Long userId);

    /**
     * Returns a Collection of all user.
     *
     * @return a {@link Collection} of {@link UserEntity}
     */
    Collection<UserEntity> getAllUser();

    /**
     * Adds the given user to the database.
     *
     * @param entity The entity that will be saved.
     * @throws EmailAlreadyInUseException If a user already exist the given id.
     */
    void addUser(UserEntity entity);

    /**
     * Deletes a user with the given id.
     *
     * @param id The id of the user.
     */
    void deleteUserById(Long id);
}
