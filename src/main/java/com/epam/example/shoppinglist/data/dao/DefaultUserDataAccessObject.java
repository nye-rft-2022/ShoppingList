package com.epam.example.shoppinglist.data.dao;

import com.epam.example.shoppinglist.data.domain.UserEntity;
import com.epam.example.shoppinglist.data.repository.UserRepository;
import com.epam.example.shoppinglist.error.UserAlreadyExistException;
import com.epam.example.shoppinglist.error.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Default implementation of {@link UserDataAccessObjectInterface}.
 */
@Component
public class DefaultUserDataAccessObject implements UserDataAccessObjectInterface{

    private UserRepository userRepository;

    @Autowired
    public DefaultUserDataAccessObject(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }

    @Override
    public Collection<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(UserEntity entity) {
        try{
            userRepository.save(entity);
        }catch (Exception e){
            throw new UserAlreadyExistException("User with id: " + entity.getId() + " already exists.");
        }
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}