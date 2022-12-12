package com.michaels.designhub.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Description: UtilsDto
 * @Author: Qiang Hu
 * @Date: 10/26/2022 9:25 AM
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UtilsDto {

    @JsonProperty("is_a_function")
    private  Boolean isFunction;

    @JsonProperty("function_name")
    private String functionName;

    @JsonProperty("function_params")
    private String functionParams;

    @JsonProperty("service_host")
    private String serviceHost;

    @JsonProperty("service_port")
    private String servicePort;

    @JsonProperty("service_uri")
    private String serviceUri;

    @JsonProperty("service_params")
    private UtilsParamsDto serviceParams;
}
