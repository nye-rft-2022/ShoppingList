package com.epam.example.shoppinglist.web.transformer;

import com.epam.example.shoppinglist.data.domain.ShoppingListEntity;
import com.epam.example.shoppinglist.data.domain.ShoppingListEntryEntity;
import com.epam.example.shoppinglist.web.domain.ShoppingListEntryView;
import com.epam.example.shoppinglist.web.domain.ShoppingListView;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.testng.Assert.*;

/**
 * Test class of {@link ShoppingListTransformer}.
 */
public class ShoppingListTransformerTest {

    private static final String ENTRY_NAME = "Item name";
    private static final int ENTRY_AMOUNT = 85;
    private static final String LIST_NAME = "List name";


    @InjectMocks
    private ShoppingListTransformer underTest;

    @Mock
    private EntryTransformer transformer;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void tearDown() {
        underTest = null;
        transformer = null;
    }

    @Test
    public void testTransformToViewShouldReturnNullWhenCalledWithNull() {
        //given
        //when
        ShoppingListView actual = underTest.transformToView(null);

        //then
        assertNull(actual);
    }

    @Test
    public void testTransformToViewShouldWhen() {
        //given
        ShoppingListEntity shoppingListEntity = createListEntity(LIST_NAME);
        List<ShoppingListEntryEntity> entries = List.of(createEntryEntity(ENTRY_NAME, ENTRY_AMOUNT));
        given(transformer.transformToView(entries)).willReturn(List.of(createEntryView(ENTRY_NAME, ENTRY_AMOUNT)));
        ShoppingListView expected = createListView(LIST_NAME);

        //when
        ShoppingListView actual = underTest.transformToView(shoppingListEntity);

        //then
        assertEquals(actual, expected);
        then(transformer).should().transformToView(entries);
    }

    @Test
    public void testTransformToEntityShouldReturnNullWhenCalledWithNull() {
        //given
        //when
        ShoppingListEntity actual = underTest.transformToEntity(null);

        //then
        assertNull(actual);
    }

    @Test
    public void testTransformToEntityShouldWhen() {
        //given
        ShoppingListView shoppingListView = createListView(LIST_NAME);
        List<ShoppingListEntryView> entries = List.of(createEntryView(ENTRY_NAME, ENTRY_AMOUNT));
        given(transformer.transformToEntity(entries)).willReturn(List.of(createEntryEntity(ENTRY_NAME, ENTRY_AMOUNT)));
        ShoppingListEntity expected = createListEntity(LIST_NAME);

        //when
        ShoppingListEntity actual = underTest.transformToEntity(shoppingListView);

        //then
        assertEquals(actual, expected);
        then(transformer).should().transformToEntity(entries);
    }

    private ShoppingListView createListView(String name){
        return ShoppingListView.builder()
                .name(name)
                .entries(
                        List.of(createEntryView(ENTRY_NAME, ENTRY_AMOUNT))
                )
                .build();
    }

    private ShoppingListEntryView createEntryView(String itemName, int quantity){
        return ShoppingListEntryView.builder()
                .itemName(itemName)
                .amount(quantity)
                .build();
    }

    private ShoppingListEntity createListEntity(String name){
        ShoppingListEntity entity = new ShoppingListEntity();
        entity.setName(name);
        entity.setEntries(
                List.of(createEntryEntity(ENTRY_NAME, ENTRY_AMOUNT))
        );
        return entity;
    }
    private ShoppingListEntryEntity createEntryEntity(String itemName, int quantity){
        ShoppingListEntryEntity entity = new ShoppingListEntryEntity();
        entity.setItemName(itemName);
        entity.setQuantity(quantity);
        return entity;
    }
}