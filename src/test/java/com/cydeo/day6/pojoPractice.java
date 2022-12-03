package com.cydeo.day6;

import com.cydeo.pojo.Region;
import com.cydeo.pojo.Regions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.cydeo.utilities.*;
import io.restassured.http.*;
import org.junit.jupiter.api.*;
import java.util.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class pojoPractice extends HRTestBase {

    /* send a get request to regions
        verify that region ids are 1,2,3,4
        verify that regions names Europe ,Americas , Asia, Middle East and Africa
        verify that count is 4
        try to use pojo as much as possible
        ignore non-used fields
     */

    @Test
    public void test1() {

        Regions regions = get("/regions").then().statusCode(200).extract().response().as(Regions.class);


        assertThat(regions.getCount(), is(4));

        List<String> actualRegionsNames = new ArrayList<>();
        List<Integer> actualRegionsIds = new ArrayList<>();

        List<Region> items = regions.getItems();
        for (Region eachRegion : items) {
            actualRegionsIds.add(eachRegion.getRegionId());
            actualRegionsNames.add(eachRegion.getRegionName());
        }

        System.out.println("actualRegionsNames = " + actualRegionsNames);
        System.out.println("actualRegionsIds = " + actualRegionsIds);


        List<String> expectedRegionsNames = Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");
        List<Integer> expectedRegionsIds = Arrays.asList(1,2,3,4);
        System.out.println("expectedRegionsNames = " + expectedRegionsNames);
        System.out.println("expectedRegionsIds = " + expectedRegionsIds);

        assertThat(actualRegionsNames, is(expectedRegionsNames));
        assertThat(actualRegionsIds, is(expectedRegionsIds));


    }

    /*
    /regions
print region id
print region name
print href
     */
    @Test
    public void test(){
        Regions newRegion = given().contentType(ContentType.JSON)
                .get("/regions")
                .then()
                .statusCode(200)
                .extract().as(Regions.class);

        Region europe = newRegion.getItems().get(1);
        System.out.println(europe);

        System.out.println(europe.getRegionId());
        System.out.println(europe.getRegionName());
        System.out.println(europe.getLinkList().get(0).getRel());


    }
}

