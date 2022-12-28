package com.michaels.designhub.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:32
 * @Version 1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GlassDetails {

    @JsonProperty("glass_qty")
    private Integer glassQty;

    @JsonProperty("glass_short_side")
    private Integer glassShortSide;

    @JsonProperty("glass_long_side")
    private Integer glassLongSide;

    @JsonProperty("order_lineitem_number")
    private String orderLineitemNumber;

    @JsonProperty("order_width")
    private BigDecimal orderWidth;

    @JsonProperty("order_height")
    private BigDecimal orderHeight;

    @JsonProperty("glass_bin")
    private String glassBin;
}
