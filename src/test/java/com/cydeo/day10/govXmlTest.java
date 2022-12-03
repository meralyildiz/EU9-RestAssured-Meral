package com.cydeo.day10;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;

public class govXmlTest {

    @Test
    public void test1() {
        //send a get request to
        //https://data.ct.gov/api/views/qm34-pq7e/rows.xml
        //get all the categories
        //get all items
        //get all zipcodes
        //get 2007 _address



        Response response = get("https://data.ct.gov/resource/y6p2-px98.xml")
                .then().statusCode(200).log().all().extract().response();

        //get all the categories
        XmlPath xmlPath = response.xmlPath();
        List<String> list = xmlPath.getList("response.rows.row.category");
        System.out.println("list = " + list);

        //get all items
        List<String> items = xmlPath.getList("response.rows.row.item");
        System.out.println("items = " + items);

        //get all zipcodes
        List<Integer> zipCodes = xmlPath.getList("response.rows.row.zipcode");
        System.out.println("zipCodes = " + zipCodes);

        //get the address
        List<String> address = xmlPath.getList("response.rows.row.location_1_address[0]");
        System.out.println("address = " + address);

        List<String> _address = xmlPath.getList("response.rows.row.@_address");
        System.out.println("_address = " + _address);


    }


}
