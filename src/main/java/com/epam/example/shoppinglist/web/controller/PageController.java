package com.epam.example.shoppinglist.web.controller;

import com.epam.example.shoppinglist.web.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controller that returns pages.
 */
@Controller
public class PageController {

    private static final String USER_PAGE_MAPPING = "/userPage/{id}";
    private static final String ROOT_MAPPING = "/";
    private static final String USER_PAGE_NAME = "UserPage";
    private static final String USER_LIST_PAGE_NAME = "UserListPage";
    private static final String USERS_MODEL_KEY = "users";
    private static final String USER_MODEL_KEY = "user";

    private UserServiceInterface userService;

    @Autowired
    public PageController(UserServiceInterface userService) {
        this.userService = userService;
    }

    /**
     * Returns the home page's name and populates it's model.
     *
     * @param model {@link Model} of the view.
     * @return The name of the home page html as a string.
     */
    @GetMapping(ROOT_MAPPING)
    public String homePage(Model model) {
        model.addAttribute(USERS_MODEL_KEY, userService.getAllUser());
        return USER_LIST_PAGE_NAME;
    }

    /**
     * Returns the home page's name and populates it's model.
     *
     * @param id The id of the user.
     * @param model {@link Model} of the view.
     * @return The name of the home page html as a string.
     */
    @GetMapping(USER_PAGE_MAPPING)
    public String userPage(@PathVariable Long id, Model model){
        model.addAttribute(USER_MODEL_KEY, userService.getUserById(id));
        return USER_PAGE_NAME;
    }
}
