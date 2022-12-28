package com.michaels.designhub.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:29
 * @Version 1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sheets {
    private int number;
    private int width;
    private int height;
    private int count;
    @JsonProperty("order_parts")
    private List<Parts> orderParts;

    @JsonProperty("waste_parts")
    private List<Parts> wasteParts;

}
