package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString

public class Region {
    //if your jsonKey and variable name are not matching, you can map it with jsonProperty
    @JsonProperty("region_id")
    private int regionId;

    @JsonProperty("region_name")
    private String regionName;

    @JsonProperty("links")
    private List<Link> linkList;

}
