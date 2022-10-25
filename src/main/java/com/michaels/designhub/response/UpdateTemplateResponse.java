package com.michaels.designhub.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author Baojian Hong
 * @Date 2022/8/26 10:15
 * @Version 1.0
 */
@Data
public class UpdateTemplateResponse {

    @JsonProperty("status_code")
    private Integer statusCode;

    @JsonProperty("status_message")
    private String statusMessage;
}
