package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HrGetRequest {
    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://44.208.31.140:1000/ords/hr";

    }
    @DisplayName("GET request to /regions")
    @Test
    public void test1() {
        Response response = RestAssured.get("/regions");

        //print the status code
        System.out.println(response.statusCode());


    }



    @Test
    public void test2(){
        Response response= RestAssured.given().accept(ContentType.JSON)
                .when().get("/regions/2");

        //verify the status code
        Assertions.assertEquals(200, response.statusCode());

        //verify content type
        Assertions.assertEquals("application/json", response.contentType());

        response.prettyPrint();

        //verify bondy contains Americas
        Assertions.assertEquals(response.body().asString().contains("Americas"), true);

    }


}
