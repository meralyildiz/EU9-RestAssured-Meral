package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartansGetRequest {

    String baseUrl = "http://44.208.31.140:8000";

    //Given Accept type application/json
    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(baseUrl + "/api/spartans");

        //printing status code from response object
        response.statusCode();

        //printing response content type from response object
        System.out.println("response.contentType() = " + response.contentType());

        //print whole result body
        response.prettyPrint();

        //how to do API testing then?
        Assertions.assertEquals(response.statusCode(), 200);

        //verify content type is application/json
        Assertions.assertEquals(response.contentType(),"application/json");


    }
//When user send GET request to api/spartans end point
    @DisplayName("GET one spartan api/spartans/3 and verify")
    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON).
        when().get(baseUrl+ "/api/spartans/3");

      //verify status code 200
        Assertions.assertEquals(200, response.statusCode());

      //Then response Content Type must be application/json
        Assertions.assertEquals("application/json", response.contentType());

      //And response body should contain Fidole
        Assertions.assertTrue(response.body().asString().contains("Fidole"));

    }
    @DisplayName("GET request to /api/hello")

    @Test
    public void test3(){
        //send request and save response inside the response object
       Response response = RestAssured.when().get(baseUrl+ "/api/hello");

        //verify status code 200
        Assertions.assertEquals(200, response.statusCode());

        //verify content type
        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());

        //verify we have headers named date
        //we use hasHeaderWithname method to verify header exist or not - it returns boolean
         Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

         //how to get any header from response using header key?
        //we use response.header(String headerName) method to get any header value
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println(response.header("Date"));

        //verify content length is 17
        Assertions.assertEquals("17", response.header("Content-Length"));

        //verify body is "Hello from Sparta"
        Assertions.assertEquals("Hello from Sparta", response.body().asString());

    }
}
