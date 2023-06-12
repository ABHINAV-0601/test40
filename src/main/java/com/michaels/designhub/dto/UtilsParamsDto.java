package com.michaels.designhub.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description: UtilsParamsDto
 * @Author: Qiang Hu
 * @Date: 10/26/2022 9:29 AM
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UtilsParamsDto {

    private List<UtilsParamStockDto> stocks;

    @JsonProperty("order_parts")
    private List<UtilsParamPartDto> order_parts;

    @JsonProperty("store_id")
    private String store_id;

    @JsonProperty("start_date")
    private String start_date;

    @JsonProperty("end_date")
    private String end_date;

    private  Boolean all;

    @JsonProperty("store_only")
    private  Boolean store_only;

    @JsonProperty("framer_usernames")
    private String[] framer_usernames;

}
