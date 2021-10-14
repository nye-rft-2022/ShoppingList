package com.epam.example.shoppinglist.web.transformer;

import com.epam.example.shoppinglist.data.domain.UserEntity;
import com.epam.example.shoppinglist.web.domain.CreateUserRequest;
import com.epam.example.shoppinglist.web.domain.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Transformer class that transforms {@link UserEntity} and {@link UserEntity} objects.
 */
@Component
public class UserTransformer {

    private ShoppingListTransformer transformer;

    @Autowired
    public UserTransformer(ShoppingListTransformer transformer) {
        this.transformer = transformer;
    }

    /**
     * Transforms a {@link Collection} of {@link UserEntity} to a {@link List} of {@link UserView}.
     *
     * @param collection The {@link Collection} of {@link UserEntity} to be transformed
     * @return The transformed {@link List} of {@link UserView} or null if the given collection is null.
     */
    public List<UserView> transform(Collection<UserEntity> collection){
        List<UserView> result = null;
        if(collection != null){
            result = collection.stream().map(this::transform).collect(Collectors.toList());
        }
        return result;
    }

    /**
     * Transforms a single {@link UserEntity} to a {@link UserView}.
     *
     * @param entity The {@link UserEntity} to be transformed.
     * @return The transformed {@link UserView} or null if the given entity is null.
     */
    public UserView transform(UserEntity entity){
        UserView result = null;
        if(entity != null){
            result = UserView.builder()
                    .id(entity.getId())
                    .userName(entity.getUserName())
                    .shoppingList(transformer.transformToView(entity.getShoppingList()))
                    .build();
        }
        return result;
    }

    /**
     * Transforms a single {@link UserView} to a {@link UserEntity}.
     *
     * @param request The {@link UserView} to be transformed.
     * @return The transformed {@link UserEntity} or null if the given entity is null.
     */
    public UserEntity transform(CreateUserRequest request){
        UserEntity result = null;
        if(request != null){
            result = new UserEntity();
            result.setEmailAddress(request.getEmailAddress());
            result.setUserName(request.getUserName());
            result.setShoppingList(transformer.transformToEntity(request.getShoppingList()));
        }
        return result;
    }
}
