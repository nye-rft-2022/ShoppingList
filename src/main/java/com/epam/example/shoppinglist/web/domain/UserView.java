package com.epam.example.shoppinglist.web.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Web layer representation of a user.
 */
@Data
@Builder
public class UserView implements Comparable<UserView>{
    private Long id;
    private String userName;
    private ShoppingListView shoppingList;

    @Override
    public int compareTo(UserView o) {
        return userName.compareTo(o.userName);
    }
}
