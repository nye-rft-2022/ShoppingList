package com.epam.apitest;
import org.junit.Test;
import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.request;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.json.simple.parser.ParseException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasKey;

public class AppTest {

    private final String BASE_URL = "http://localhost:8080";

    public JSONObject readJSON(String fileName) {
    Object obj = new Object();
    try {
        obj = new JSONParser().parse(new FileReader(System.getProperty("user.dir") + "/src/test/resources/" + fileName));
    } catch (IOException | ParseException e) {
        e.printStackTrace();
    }

    return (JSONObject) obj;
    }

    public String getUserId(String userName){
        return String.valueOf(get(BASE_URL + "/user/users").path("find {it.userName == '" + userName + "'}.id"));
    }
    
    public void deleteUser(String userName) {
        String userId = getUserId(userName);
        Response response = request("delete", BASE_URL + "/user/delete/" + userId);

        response.then()
            .statusCode(200);
    }

    @Test
    public void When_UsersListIsRequested_Expect_StatusOk() {
        Response response = request("get", BASE_URL + "/user/users");
        
        response.then()
            .statusCode(200);
    }

    @Test
    public void When_UsersListIsRequested_Expect_ItsNotEmpty() {
        Response response = request("get", BASE_URL + "/user/users");
        
        response.then()
            .body("size()", is(not(empty())));
    }

    @Test
    public void When_UsersListIsRequested_Expect_ProperFields() {
        Response response = request("get", BASE_URL + "/user/users");
        
        response.then()
            .body("[0]", hasKey("id"))
            .body("[0]", hasKey("userName"))
            .body("[0]", hasKey("shoppingList"))
            .body("[0].shoppingList", hasKey("name"))
            .body("[0].shoppingList", hasKey("name"));
    }

    @Test
    public void When_UserIsRequested_Expect_StatusOk() {
        String userId = String.valueOf(get(BASE_URL + "/user/users").path("[0].id"));
        Response response = request("get", BASE_URL + "/user/" + userId);

        response.then()
            .statusCode(200);
    }

    @Test
    public void When_UserIsRequested_Expect_ProperFields() {
        String userId = String.valueOf(get(BASE_URL + "/user/users").path("[0].id"));
        Response response = request("get", BASE_URL + "/user/" + userId);
        
        response.then()
            .body("", hasKey("id"))
            .body("", hasKey("userName"))
            .body("", hasKey("shoppingList"))
            .body("shoppingList", hasKey("name"))
            .body("shoppingList", hasKey("entries"));
    }

    @Test
    public void When_InvalidUserIsRequested_Expect_ErrorResponse() {
        Response response = request("get", BASE_URL + "/user/notValid");

        response.then()
            .statusCode(400);
    }

    @Test
    public void When_NonExistingUserIsRequested_Expect_ErrorResponse() {
        Response response = request("get", BASE_URL + "/user/500");

        response.then()
            .statusCode(404);
    }

    @Test
    public void When_CreatingNewUser_StatusOk() {
        RequestSpecification request = given().log().ifValidationFails();
        JSONObject testUserJson = readJSON("testUser1.json");

        request.header("Content-Type", "application/json");
        request.body(JSONObject.toJSONString(testUserJson));

        Response response = request.request("post", BASE_URL + "/user/add");

        response.then()
            .statusCode(200);

        deleteUser("testUserName");
    }

    @Test
    public void When_CreatingNewUserWithAlreadyExistingEmail_ErrorResponse() {
        RequestSpecification request = given().log().ifValidationFails();
        JSONObject testUserJson = readJSON("testUser2.json");

        request.header("Content-Type", "application/json");
        request.body(JSONObject.toJSONString(testUserJson));

        Response response = request.request("post", BASE_URL + "/user/add");

        response.then()
            .statusCode(200);
    }
}
