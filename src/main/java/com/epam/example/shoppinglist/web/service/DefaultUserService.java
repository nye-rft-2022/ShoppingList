package com.epam.example.shoppinglist.web.service;

import com.epam.example.shoppinglist.data.dao.UserDataAccessObjectInterface;
import com.epam.example.shoppinglist.data.domain.UserEntity;
import com.epam.example.shoppinglist.web.domain.CreateUserRequest;
import com.epam.example.shoppinglist.web.domain.UserView;
import com.epam.example.shoppinglist.web.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Default implementation of {@link UserServiceInterface}.
 */
@Service
public class DefaultUserService implements UserServiceInterface{

    private UserDataAccessObjectInterface userDataAccessObject;
    private UserTransformer transformer;

    @Autowired
    public DefaultUserService(UserDataAccessObjectInterface userDataAccessObject, UserTransformer transformer) {
        this.userDataAccessObject = userDataAccessObject;
        this.transformer = transformer;
    }

    @Override
    public UserView getUserById(Long id) {
        UserEntity userEntity = userDataAccessObject.getUserById(id);
        return transformer.transform(userEntity);
    }

    @Override
    public List<UserView> getAllUser() {
        Collection<UserEntity> userEntities  = userDataAccessObject.getAllUser();
        List<UserView> users = transformer.transform(userEntities);
        return users.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public void addUser(CreateUserRequest request) {
        UserEntity userEntity = transformer.transform(request);
        userDataAccessObject.addUser(userEntity);
    }

    @Override
    public void deleteUserById(Long id) {
        userDataAccessObject.deleteUserById(id);
    }
}