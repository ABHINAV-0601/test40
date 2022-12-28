package com.michaels.designhub.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:27
 * @Version 1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NonPrintedLayoutParent {
    @JsonProperty("store_id")
    private String storeId;

    @JsonProperty("non-printed-layout")
    private List<NonPrintedLayout> nonPrintedLayout;
}
