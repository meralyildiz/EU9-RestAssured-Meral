package com.cydeo.day4;

import com.cydeo.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.*;

public class ORDSApiWithJsonPath extends HRTestBase {

    @DisplayName("GET request to Countries")
    @Test
    public void test1() {
        Response response = get("/countries");

        //get the second country name with JsonPath

        //to use jsonpath we assign response to JsonPath
        JsonPath jsonPath = response.jsonPath();
       // response.prettyPrint();
        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);


        // get all country ids
        //items.conutry_id
        List<String> allCountryIds = jsonPath.getList("items.country_id");
        System.out.println(allCountryIds);

        //get all country names where their region id is equal to 2                //it: iterator= check
        List<String> countryNameWithRegionId2 = jsonPath.getList("items.findAll{it.region_id==2}.country_name");
        System.out.println(countryNameWithRegionId2);

    }
    @Test
    public void test2(){
        Response response = given().queryParam("limit", 107)
                .when().get("/employees");

        //get me all email of employees who is working as IT_PROG
        JsonPath jsonPath = response.jsonPath();
        List<String> employeeITProgs = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println(employeeITProgs);

        List<String> employeeITProgs2 = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.first_name");
        System.out.println(employeeITProgs2);

        //get me first name of employees who is making more than 10000
        List<String> salary = jsonPath.getList("items.findAll{it.salary>10000}.first_name");
        System.out.println(salary);

        //get the max salary first name
        String kingFirstName = jsonPath.getString("items.max{it.salary}.first_name");
        System.out.println(kingFirstName);

        String kingNameWithPathMethod = response.path("items.max{it.salary}.first_name");
        System.out.println(kingNameWithPathMethod);
    }
}
