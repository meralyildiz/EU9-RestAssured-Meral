package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanTestBase {
    @BeforeAll
    //save baserUrl inside this variable so that we don't need to type each http method
    public static void init() {
        RestAssured.baseURI = "http://44.208.31.140:8000";

        String dbUrl = "jdbc:oracle:thin:@ 44.208.31.140:1521:XE";
        String dbUsername = "SP";
        String dbPassword = "SP";
    }

}
