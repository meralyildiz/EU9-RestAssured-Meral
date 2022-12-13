package com.cydeo.day12;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class MockApi {

    //https://fb8d7079-9618-450b-9f73-30119d4a811c.mock.pstmn.io

    @Test
    public void test1(){
        given().baseUri("https://fb8d7079-9618-450b-9f73-30119d4a811c.mock.pstmn.io")
                .accept(ContentType.JSON)
                .when()
                .get("/customer")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstName", is("John"));


    }

    //negative scenario
    @Test
    public void test2(){
        given().baseUri("https://fb8d7079-9618-450b-9f73-30119d4a811c.mock.pstmn.io")
                .accept(ContentType.JSON)
                .when()
                .get("/employees")
                .prettyPrint();

    }
}
