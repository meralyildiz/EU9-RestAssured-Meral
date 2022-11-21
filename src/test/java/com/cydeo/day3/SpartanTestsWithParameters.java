package com.cydeo.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;



public class SpartanTestsWithParameters {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://44.208.31.140:8000";
    }

    /*   Given accept type is Json
          And Id parameter value is 5
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json
          And "Blythe" should be in response payload
       */
    @DisplayName("GET request to api/spartans/{id} with ID 5")
    @Test
    public void test1(){
    Response response = given().
                                accept(ContentType.JSON)
                                .and().pathParams("id", 5)
                        .when()
                                 .get("/api/spartans/{id}");

        //verify status code
        assertEquals(200, response.statusCode());

        //verify content type
        assertEquals("application/json", response.contentType() );

        //verify Blythe in the json payload/body
        assertTrue(response.body().asString().contains("Blythe"));

    }

    /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON).
                    and().pathParams("id", 500)
                    .when().get("/api/spartans/{id}");

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals("application/json", response.contentType());
        Assertions.assertTrue(response.body().asString().contains("Not Found"));

    }

    /*
        Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */
    @Test
    public void test3(){
    Response response = given().log().all()
                .accept(ContentType.JSON)
                .and().queryParam("nameContains", "e")
                .and().queryParam("gender", "Female")
        .when()
                .get("/api/spartans/search");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
    }

    @DisplayName("GET request to /api/spartans/search with Query Params (MAP)")
    @Test
    public void test4(){
        //create a map and add query parameters
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("nameContains", "e");
        queryMap.put("gender", "Female");

        Response response = given().
                            log().all()
                .accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when()
                .get("/api/spartans/search");


    }
}
