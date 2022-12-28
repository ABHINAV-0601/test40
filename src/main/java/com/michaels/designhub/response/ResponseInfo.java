package com.michaels.designhub.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:28
 * @Version 1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseInfo {

    @JsonProperty("cutGLib_version")
    private String cutGLibVersion;

    private List<Layouts> layouts;

    private List<String> log;

    @JsonProperty("unoptimized_order_parts")
    private List<UnoptimizedOrderParts> unoptimizedOrderParts;
}
