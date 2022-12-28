package com.michaels.designhub.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:23
 * @Version 1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchGSOAndLayoutOptimizationResponse {
    @JsonProperty("store_id")
    private String storeId;

    @JsonProperty("printed-layout")
    private List<PrintedLayout> printedLayouts;

    @JsonProperty("non-printed-layout")
    private NonPrintedLayoutParent nonPrintedLayoutParent;
}
