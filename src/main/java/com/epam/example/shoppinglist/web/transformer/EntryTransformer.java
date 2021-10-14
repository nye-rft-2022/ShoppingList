package com.epam.example.shoppinglist.web.transformer;

import com.epam.example.shoppinglist.data.domain.ShoppingListEntryEntity;
import com.epam.example.shoppinglist.web.domain.ShoppingListEntryView;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Transformer class that transforms {@link ShoppingListEntryEntity} objects to {@link ShoppingListEntryView}.
 */
@Component
public class EntryTransformer {

    /**
     * Transforms a {@link Collection} of {@link ShoppingListEntryEntity} to a {@link List} of {@link ShoppingListEntryView}.
     *
     * @param entries The collection to be transformed.
     * @return A transformed {@link List} of {@link ShoppingListEntryEntity} or null id called with null.
     */
    public List<ShoppingListEntryView> transformToView(Collection<ShoppingListEntryEntity> entries){
        List<ShoppingListEntryView> result = null;
        if(entries != null){
            result = entries.stream()
                    .map(this::transformToView)
                    .collect(Collectors.toList());
        }
        return result;
    }

    /**
     * Transforms a {@link Collection} of {@link ShoppingListEntryView} to a {@link List} of {@link ShoppingListEntryEntity}.
     *
     * @param entries The collection to be transformed.
     * @return A transformed {@link List} of {@link ShoppingListEntryEntity} or null id called with null.
     */
    public List<ShoppingListEntryEntity> transformToEntity(Collection<ShoppingListEntryView> entries){
        List<ShoppingListEntryEntity> result = null;
        if(entries != null){
            result = entries.stream()
                    .map(this::transformToEntity)
                    .collect(Collectors.toList());
        }
        return result;
    }

    private ShoppingListEntryView transformToView(ShoppingListEntryEntity entity){
        ShoppingListEntryView result = null;
        if(entity != null){
            result = ShoppingListEntryView.builder()
                    .itemName(entity.getItemName())
                    .amount(entity.getQuantity())
                    .build();
        }
        return result;
    }

    private ShoppingListEntryEntity transformToEntity(ShoppingListEntryView view){
        ShoppingListEntryEntity result = null;
        if(view != null){
            result = new ShoppingListEntryEntity();
            result.setItemName(view.getItemName());
            result.setQuantity(view.getAmount());
        }
        return result;
    }
}