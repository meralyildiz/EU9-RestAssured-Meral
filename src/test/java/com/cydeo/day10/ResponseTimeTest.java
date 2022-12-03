package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ResponseTimeTest extends SpartanAuthTestBase {

    @Test
    public void test1(){
        Response response = given()
                .auth().basic("admin", "admin")
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then()
               // .time(lessThanOrEqualTo(1100l)) --> response time should be less than the given time limit
                .time(both(greaterThan(500l)).and(lessThanOrEqualTo(1100l))) //--> response time should between the given time limit
                .extract().response();

        System.out.println("response.getTime() = " + response.getTime());

    }
}
