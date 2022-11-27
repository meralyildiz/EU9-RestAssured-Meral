package com.cydeo.day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import static io.restassured.RestAssured.baseURI;

public class CYTrainingApiWithJsonPath {
    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "https://api.training.cydeo.com";

    }
    @DisplayName("GET Request to individual student")
    @Test
    public void test1(){
        //send a get request to student id 23401 as a path parameter and accept header application/json
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/student/{id}");

        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertEquals("chunked", response.header("transfer-encoding"));

        //verify Date header exists
        //we use hasHeaderWithName method to verify header exist or not - it returns boolean
        Assertions.assertTrue(response.headers().hasHeaderWithName("date"));
        assertEquals("Leopold", response.path("students[0].firstName") );
        assertEquals("22", response.path("students[0].batch").toString());
        assertEquals("N/A", response.path("students[0].section"));
        assertEquals("ocolliber1@apache.org", response.path("students[0].contact.emailAddress"));
        assertEquals("Jayo", response.path("students[0].company.companyName"));
        assertEquals("Kentucky", response.path("students[0].company.  address.state"));



        //assert that
            /*
            "firstName": "Leopold",
            "batch": 22,
            "section": "N/A",
            "emailAddress": "ocolliber1@apache.org",
            "companyName": "Jayo",
            "state": "Kentucky",
            "zipCode": 31560
            using JsonPath




                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606


             */


    }
}
