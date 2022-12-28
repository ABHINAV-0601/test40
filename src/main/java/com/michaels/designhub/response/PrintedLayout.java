package com.michaels.designhub.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:27
 * @Version 1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrintedLayout {

    @JsonProperty("glass_type")
    private String glassType;

    @JsonProperty("response_info")
    private ResponseInfo responseInfo;
}
