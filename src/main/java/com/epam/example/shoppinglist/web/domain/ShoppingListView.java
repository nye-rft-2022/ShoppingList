package com.epam.example.shoppinglist.web.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Web layer representation of a shopping list.
 */
@Builder
@Data
public class ShoppingListView {
    private String name;
    private List<ShoppingListEntryView> entries;
}
