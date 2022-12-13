package com.cydeo.utilities;

import io.restassured.RestAssured.*;
import io.restassured.filter.log.*;
import io.restassured.http.*;
import io.restassured.specification.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public abstract class SpartanTestBase {
    @BeforeAll
    //save baserUrl inside this variable so that we don't need to type each http method
    public static void init() {
        baseURI = ConfigurationReader.getProperty("qa3_api_url");

    }

    @AfterAll
    public static void tearDown(){
        reset();

    }

}
