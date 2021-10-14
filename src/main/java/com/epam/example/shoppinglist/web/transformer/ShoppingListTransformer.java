package com.epam.example.shoppinglist.web.transformer;

import com.epam.example.shoppinglist.data.domain.ShoppingListEntity;
import com.epam.example.shoppinglist.web.domain.ShoppingListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Transformer class that transforms {@link ShoppingListEntity} objects to {@link ShoppingListView}.
 */
@Component
public class ShoppingListTransformer {

    private EntryTransformer transformer;

    @Autowired
    public ShoppingListTransformer(EntryTransformer transformer) {
        this.transformer = transformer;
    }

    /**
     * Transforms a {@link ShoppingListEntity} to a {@link ShoppingListView}.
     *
     * @param entity A {@link ShoppingListEntity} to be transformed.
     * @return A transformed {@link ShoppingListView} or null if called with null.
     */
    public ShoppingListView transformToView(ShoppingListEntity entity){
        ShoppingListView result = null;
        if(entity != null){
            result = ShoppingListView.builder()
                    .name(entity.getName())
                    .entries(transformer.transformToView(entity.getEntries()))
                    .build();
        }
        return result;
    }

    /**
     * Transforms a {@link ShoppingListView} to a {@link ShoppingListEntity}.
     *
     * @param view A {@link ShoppingListView} to be transformed.
     * @return A transformed {@link ShoppingListEntity} or null if called with null.
     */
    public ShoppingListEntity transformToEntity(ShoppingListView view){
        ShoppingListEntity result = null;
        if(view != null){
            result = new ShoppingListEntity();
            result.setName(view.getName());
            result.setEntries(transformer.transformToEntity(view.getEntries()));
        }
        return result;
    }
}