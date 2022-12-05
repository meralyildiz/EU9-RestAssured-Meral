package com.cydeo.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class ParametrizedTestInJUnit5 {

    @ParameterizedTest //coming from junit.jupiter library
    @ValueSource(ints = {1,5,3,41,12,6,7,10,21,32})
    public void testMultipleNumbers(int number){
        System.out.println("number = " + number);

        Assertions.assertTrue(number>5);
    }

    @ParameterizedTest
    @ValueSource(strings = {"john","abbas","ali","TJ"})
    public void testMultipleNames(String name){
        System.out.println("name = " + name);
    }

    // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
    // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code 200

    @ParameterizedTest
    @ValueSource(ints = {22030,22031, 22032, 22033 , 22034, 22035, 22036})
    public void zipCodeTest (int zipCodes){
        given()

                .baseUri("https://api.zippopotam.us")
                .pathParam("zipcode", zipCodes)
                .log().all()
                .when()
                .get("/us/{zipcode}")
                .then().log().all()
                .statusCode(200);

    }

}
