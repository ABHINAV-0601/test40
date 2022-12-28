package com.michaels.designhub.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:30
 * @Version 1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Parts {
    private int number;
    private int stock;
    private BigDecimal width;
    private BigDecimal height;
    private String orderId;

    @JsonProperty("aX")
    private BigDecimal aX;

    @JsonProperty("aY")
    private BigDecimal aY;

    private boolean rotated;
}
