package com.epam.example.shoppinglist.web.domain;

import lombok.Builder;
import lombok.Data;

/**
 * Web layer representation of a shopping list entry.
 */
@Builder
@Data
public class ShoppingListEntryView {
    private String itemName;
    private int amount;
}
