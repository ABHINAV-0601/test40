package com.michaels.designhub.dto;

import lombok.Data;

/**
 * @Description: UtilsDto
 * @Author: Qiang Hu
 * @Date: 10/26/2022 9:25 AM
 */
@Data
public class UtilsDto {
    private  Boolean isFunction;
    private String functionName;
    private String functionParams;
    private String serviceHost;
    private String servicePort;
    private String serviceUri;
    private UtilsParamsDto orderParts;
}
