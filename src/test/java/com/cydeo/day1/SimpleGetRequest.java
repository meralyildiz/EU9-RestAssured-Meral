package com.cydeo.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url = "http://44.208.31.140:8000/api/spartans";

    @Test
    public void test1(){

        //send a get request and save response inside the response object (alt+enter)
        Response response = RestAssured.get(url);

        //print response status code
        System.out.println("response.statusCode() = " + response.statusCode());

        //print response body
        response.prettyPrint();

    }


}
