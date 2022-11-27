package com.cydeo.day2;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Practice {

     /*
        Given accept type is application/json
        When user sends get request to /regions/2
        Then response status code must be 200
        and content type equals to application/json
        and response body contains   Americas
     */

    @Test
    public void test1(){

        RestAssured.baseURI = "http://44.211.140.215:1000/ords/hr";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/regions/2");

        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals("application/json",response.contentType());
        Assertions.assertTrue(response.body().asString().contains("Americas"));

    }

    /*
      Given Accept type application/json
      When user send GET request to api/spartans end point
      Then status code must 200
      And response Content Type must be application/json
      And response body should include spartan result
    */

    @Test
    public void test2(){

        RestAssured.baseURI = "http://44.211.140.215:8000";
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals("application/json", response.contentType());

    }

    /*
        Given accept header is application/json
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json
        and json body should contain Fidole
     */

    @Test
    public void test3(){

        RestAssured.baseURI = "http://44.208.31.140:8000";
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans/3");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json",response.contentType());
        response.prettyPrint();

    }

    /*
        Given no headers provided
        When Users send GET request to /api/hello
        Then response status code should be 200
        And Content type header should be “text/plain;charset=UTF-8”
        And header should contain date
        And Content-Length should be 17
        And body should be “Hello from Sparta"
    */

    @Test
    public void test4(){

        RestAssured.baseURI = "http://44.208.31.140:8000";
        Response response = RestAssured.given().when().get("/api/hello");

        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertTrue(response.header("Content-Type").equals("text/plain;charset=UTF-8"));
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));
        Assertions.assertEquals("17",response.header("Content-Length"));
        Assertions.assertTrue(response.body().asString().equals("Hello from Sparta"));

        response.prettyPrint();

    }

     /*TASK
    Given Accept type application/xml
    When user send GET request to /api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;charset=UTF-8
    */

    @Test
    public void test5(){

        RestAssured.baseURI = "http://44.208.31.140:8000";
        Response response = RestAssured.given().accept("application/xml")
                .when().get("/api/spartans/10");

        Assertions.assertEquals(406, response.statusCode());
        Assertions.assertTrue(response.header("Content-Type").equals("application/xml;charset=UTF-8"));

    }

    /*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
     */

    @Test
    public void test6(){

        RestAssured.baseURI = "http://44.208.31.140:1000/ords/hr";

        Response response = RestAssured.given().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals("application/json",response.contentType());
        Assertions.assertTrue(response.body().asString().contains("United States of America"));


    }

    @Test
    public void test7(){

        RestAssured.baseURI = "http://44.208.31.140:1000/ords/hr";

        Response response= RestAssured.given().queryParam("q", "{job_id:IT_PROG}").
                when().get("/employees");

        List<String> list = response.jsonPath().getList("items.first_name");

        System.out.println("list = " + list);

        response.prettyPrint();

    }

    @Test
    public void test8(){
        RestAssured.baseURI = "http://44.208.31.140:8000";

        RestAssured.given().accept(ContentType.JSON);
        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 5)
                .get("/api/spartans/{id}");
        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals("application/json",response.contentType());
        Assertions.assertTrue(response.body().asString().contains("Blythe"));

    }
    /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */

    @Test
    public void test9(){
        RestAssured.baseURI = "http://44.208.31.140:8000";
        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 500)
                .get("/api/spartans/{id}");

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals("application/json", response.contentType());
        Assertions.assertTrue(response.body().asString().contains("Not Found"));
    }
    /*
            Given accept type is Json
            And query parameter values are:
            gender|Female
            nameContains|e
            When user sends GET request to /api/spartans/search
            Then response status code should be 200
            And response content-type: application/json
            And "Female" should be in response payload
            And "Janette" should be in response payload
   */

    @Test
    public void test10(){
    RestAssured.baseURI = "http://44.208.31.140:8000";
        Response response = RestAssured.given().accept(ContentType.JSON).
                and().queryParam("gender", "female", "nameContains", "e")
                .when().get("/api/spartans/search");

        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals("application/json",response.contentType());
        Assertions.assertTrue(response.body().asString().contains("Female"));

    Assertions.assertTrue(response.body().asString().contains("Janette"));
    }


    /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
   */
    @Test
    public void test11(){
        RestAssured.baseURI = "http://44.208.31.140:8000";

        Response response= RestAssured.given().accept(ContentType.JSON)
                .and().pathParams("id",10)
                .when().get("api/spartans/{id}");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json", response.contentType());
        Assertions.assertEquals(10, response.path("id").toString());
        Assertions.assertEquals("Lorenza", response.path("name"));
        Assertions.assertEquals("Female", response.path("gender"));
        Assertions.assertEquals("3312820936", response.path("phone").toString());
    }

    public void test12(){
        RestAssured.baseURI = "https://api.training.cydeo.com";
        RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 14)
                .when().get("/student/{id}")
                .then()
                .statusCode(200)
                .contentType("application/json;charset=UTF-8")
                .body("student[0].firstName", Matchers.is("Glen"),
                        "student[0].batch", Matchers.is(3),
                        "students[0].contact.emailAddress",Matchers.is("lroutham0@opera.com"),
                        "students[0].company.companyName", Matchers.is("Gabtune"),
                        "students[0].company.address.zipCode", Matchers.is(72475)).log().all().extract();
    }

    /*
        Given accept type is json
        And path param id is 10
        When user sends a get request to "api/spartans/{id}"
        Then status code is 200
        And content-type is "application/json"
        And response payload values match the following:
             id is 10,
             name is "Lorenza",
             gender is "Female",
             phone is 3312820936
      */
    @Test
    public void test14(){
        baseURI = "http://44.208.31.140:1000/ords/hr";

        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");


        List<String> list = response.path("items.first_name");
        System.out.println("list = " + list);


    }

    //get the second country name with JsonPath
    //get all country ids
    //get all country names where their region id is equal to 2
    // get me all email of employees who is working as IT_PROG
    //get me first name of employees who is making more than 10000
    //get the max salary first_name

    @Test
    public void test15(){
        baseURI = "http://44.208.31.140:1000/ords/hr";

       Response response = given().accept(ContentType.JSON)
               .when().get("/countries");

        //System.out.println("response.path(\"items[1].country_name\") = " + response.path("items[1].country_name"));

        JsonPath jsonPath = response.jsonPath();
        String secondCountry = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountry = " + secondCountry);

        List<String> countryIds= jsonPath.getList("items.country_id");
        System.out.println("countryIds = " + countryIds);

        Response response2 = given().when().queryParam("q", "{\"region_id\": 2}")
                .and().get("/countries");

        JsonPath jsonPath2 = response2.jsonPath();
        List<Object> countryNames = jsonPath2.getList("items.country_name");
        System.out.println("countryNames = " + countryNames);
    }



}

