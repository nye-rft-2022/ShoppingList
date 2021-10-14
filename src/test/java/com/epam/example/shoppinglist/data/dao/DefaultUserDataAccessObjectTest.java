package com.epam.example.shoppinglist.data.dao;

import static org.mockito.BDDMockito.*;
import static org.testng.Assert.assertEquals;

import com.epam.example.shoppinglist.data.domain.ShoppingListEntity;
import com.epam.example.shoppinglist.data.domain.ShoppingListEntryEntity;
import com.epam.example.shoppinglist.data.domain.UserEntity;
import com.epam.example.shoppinglist.data.repository.UserRepository;
import com.epam.example.shoppinglist.error.EmailAlreadyInUseException;
import com.epam.example.shoppinglist.error.UserNotFoundException;
import org.mockito.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Test class of {@link DefaultUserDataAccessObject}.
 */
public class DefaultUserDataAccessObjectTest {

    private static final Integer FIRST_ITEM_QUANTITY = 8;
    private static final Integer SECOND_ITEM_QUANTITY = 10;
    private static final Long FIRST_ENTRY_ID = 1486L;
    private static final Long SECOND_ENTRY_ID = 85996L;
    private static final Long USER_ID = 420L;
    private static final Long LIST_ID = 1596L;
    private static final String USER_NAME = "Test User";
    private static final String LIST_NAME = "Test List";
    private static final String FIRST_ITEM_NAME = "Test Item 420 XXL";
    private static final String SECOND_ITEM_NAME = "Test Item 2";

    @InjectMocks
    private DefaultUserDataAccessObject underTest;

    @Mock
    private UserRepository userRepository;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void tearDown() {
        underTest = null;
    }

    @Test
    public void testGetUserByIdShouldReturnUserWhenCalledWithExistingId() {
        //given
        UserEntity expected = createUser();
        given(userRepository.findById(USER_ID)).willReturn(createOptionalUser());

        //when
        UserEntity actual = underTest.getUserById(USER_ID);

        //then
        assertEquals(actual, expected);
        then(userRepository).should().findById(USER_ID);
    }



    @Test(expectedExceptions = UserNotFoundException.class)
    public void testGetUserByIdShouldThrowUserNotFoundWhenCalledWithNonExistingId() {
        //given
        given(userRepository.findById(USER_ID)).willThrow(UserNotFoundException.class);

        //when
        underTest.getUserById(USER_ID);

        //then exception thrown
    }

    @Test
    public void testGetAllUserShouldReturnListOfUserWhenCalled() {
        //given
        Collection<UserEntity> expected = List.of(createUser());
        given(userRepository.findAll()).willReturn(List.of(createUser()));

        //when
        Collection<UserEntity> actual = underTest.getAllUser();

        //then
        assertEquals(actual, expected);
        then(userRepository).should().findAll();
    }

    @Test
    public void testAddUserShouldSaveTheUserWhenCalledWithNonExistingId() {
        //given
        UserEntity user = createUser();

        //when
        underTest.addUser(user);

        //then
        then(userRepository).should().save(user);
    }

    @Test(expectedExceptions = EmailAlreadyInUseException.class)
    public void testAddUserShouldThrowUserAlreadyExistExceptionWhenCalledWithExistingEmail() {
        //given
        UserEntity user = createUser();
        willThrow(DataIntegrityViolationException.class).given(userRepository).save(user);

        //when
        underTest.addUser(user);

        //then exception thrown
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testAddUserShouldThrowRuntimeExceptionWhenUnhandledExceptionHappens() {
        //given
        UserEntity user = createUser();
        willThrow(Exception.class).given(userRepository).save(user);

        //when
        underTest.addUser(user);

        //then exception thrown
    }

    @Test
    public void testDeleteUserByIdShouldDelegateToRepository() {
        //given
        //when
        underTest.deleteUserById(USER_ID);

        //then
        then(userRepository).should().deleteById(USER_ID);
    }

    @Test(expectedExceptions = UserNotFoundException.class)
    public void testDeleteUserByIdShouldThrowUserNotFoundExceptionWhenCalledWithNonExistingId() {
        //given
        willThrow(EmptyResultDataAccessException.class).given(userRepository).deleteById(USER_ID);

        //when
        underTest.deleteUserById(USER_ID);

        //then exception thrown
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testDeleteUserByIdShouldThrowRuntimeExceptionWhenUnhandledExceptionHappens() {
        //given
        willThrow(Exception.class).given(userRepository).deleteById(USER_ID);

        //when
        underTest.deleteUserById(USER_ID);

        //then exception thrown
    }

    private Optional<UserEntity> createOptionalUser(){
        return Optional.of(createUser());
    }

    private UserEntity createUser() {
        UserEntity user = new UserEntity();
        user.setId(USER_ID);
        user.setUserName(USER_NAME);
        user.setShoppingList(createShoppingList());
        return user;
    }

    private ShoppingListEntity createShoppingList() {
        ShoppingListEntity entity = new ShoppingListEntity();
        entity.setId(LIST_ID);
        entity.setName(LIST_NAME);
        entity.setEntries(createListItems());
        return entity;
    }

    private List<ShoppingListEntryEntity> createListItems() {
        return List.of(
                createEntry(FIRST_ENTRY_ID, FIRST_ITEM_QUANTITY, FIRST_ITEM_NAME),
                createEntry(SECOND_ENTRY_ID, SECOND_ITEM_QUANTITY, SECOND_ITEM_NAME)
        );
    }

    private ShoppingListEntryEntity createEntry(Long id, Integer quantity, String itemName){
        ShoppingListEntryEntity entry = new ShoppingListEntryEntity();
        entry.setId(id);
        entry.setQuantity(quantity);
        entry.setItemName(itemName);
        return entry;
    }


}