package com.epam.example.shoppinglist.web.transformer;

import com.epam.example.shoppinglist.data.domain.ShoppingListEntryEntity;
import com.epam.example.shoppinglist.web.domain.ShoppingListEntryView;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Test class of {@link EntryTransformer}.
 */
public class EntryTransformerTest {

    private static final int ITEM_AMOUNT = 96;
    private static final String ITEM_NAME = "Item name";

    @InjectMocks
    private EntryTransformer underTest;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void tearDown() {
        underTest = null;
    }

    @Test
    public void testTransformToViewShouldReturnNullWhenCalledWithNull() {
        //given
        //when
        List<ShoppingListEntryView> actual = underTest.transformToView(null);

        //then
        assertNull(actual);
    }

    @Test
    public void testTransformToViewShouldReturnAListOfEntryViewsWhenCalledWithACollectionOfEntryEntities() {
        //given
        List<ShoppingListEntryView> expected = List.of(createEntryView(ITEM_NAME, ITEM_AMOUNT));
        List<ShoppingListEntryEntity> entityList = List.of(createEntity(ITEM_NAME, ITEM_AMOUNT));

        //when
        List<ShoppingListEntryView> actual = underTest.transformToView(entityList);

        //then
        assertEquals(actual, expected);
    }

    @Test
    public void testTransformToEntityShouldReturnNullWhenCalledWithNull() {
        //given
        List<ShoppingListEntryEntity> expected = List.of(createEntity(ITEM_NAME, ITEM_AMOUNT));
        List<ShoppingListEntryView> viewList = List.of(createEntryView(ITEM_NAME, ITEM_AMOUNT));

        //when
        List<ShoppingListEntryEntity> actual = underTest.transformToEntity(viewList);

        //then
        assertEquals(actual, expected);
    }

    @Test
    public void testTransform() {
        //given
        //when
        List<ShoppingListEntryEntity> actual = underTest.transformToEntity(null);

        //then
        assertNull(actual);
    }

    private ShoppingListEntryEntity createEntity(String itemName, int itemAmount) {
        ShoppingListEntryEntity entity = new ShoppingListEntryEntity();
        entity.setItemName(itemName);
        entity.setQuantity(itemAmount);
        return entity;
    }

    private ShoppingListEntryView createEntryView(String name, int amount){
        return ShoppingListEntryView.builder()
                .itemName(name)
                .amount(amount)
                .build();
    }
}