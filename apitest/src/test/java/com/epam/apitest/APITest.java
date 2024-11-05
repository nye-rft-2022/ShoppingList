package com.epam.apitest;

import org.junit.Test;
import java.io.File;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasKey;

public class APITest {

        private final String BASE_URL = "http://localhost:8080";

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
                String userId = given()
                                .when()
                                .baseUri(BASE_URL + "/user/users")
                                .when()
                                .get()
                                .body()
                                .path("[0].id")
                                .toString();

                Response response = request("get", BASE_URL + "/user/" + userId);

                response.then()
                                .statusCode(200);
        }

        @Test
        public void When_UserIsRequested_Expect_ProperFields() {
                String userId = given()
                                .when()
                                .baseUri(BASE_URL + "/user/users")
                                .when()
                                .get()
                                .body()
                                .path("[0].id")
                                .toString();

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
        public void When_CreatingNewUserWithAlreadyExistingEmail_ErrorResponse() {
                RequestSpecification request = given().log().ifValidationFails();

                File jsonDataInFile = new File(getClass().getClassLoader().getResource("testUser2.json").getFile());

                request.header("Content-Type", "application/json");
                request.body(jsonDataInFile);

                Response response = request.request("post", BASE_URL + "/user/add");

                response.then()
                                .statusCode(400);
        }
}
