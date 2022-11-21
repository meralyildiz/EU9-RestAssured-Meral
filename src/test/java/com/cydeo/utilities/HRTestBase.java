package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.baseURI;


public abstract class HRTestBase {

    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://44.208.31.140:1000/ords/hr";

    }

}
