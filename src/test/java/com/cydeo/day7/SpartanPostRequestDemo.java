package com.cydeo.day7;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanPostRequestDemo extends SpartanTestBase {
    /*
  Given accept type and Content type is JSON
  And request json body is:
  {
    "gender":"Male",
    "name":"Severus",
    "phone":8877445596
 }
  When user sends POST request to '/api/spartans'
  Then status code 201
  And content type should be application/json
  And json payload/response/body should contain:
  "A Spartan is Born!" message
  and same data what is posted
*/
    @Test
    public void method1() {
        String requestJsonBody = "{\"gender\":\"Male\",\n" +
                "\"name\":\"Severus\",\n" +
                "\"phone\":8877445596}";
        Response response = given().accept(ContentType.JSON).and() //I want from you JsonContentType
                .contentType(ContentType.JSON) //I send you as content type Json
                .body(requestJsonBody)
                .when().post("/api/spartans");

        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMessage));
        assertThat(response.path("data.name"), is("Severus"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(8877445596L));


    }
}
