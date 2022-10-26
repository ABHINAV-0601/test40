package com.michaels.designhub.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description: UtilsParamsDto
 * @Author: Qiang Hu
 * @Date: 10/26/2022 9:29 AM
 */
@Data
public class UtilsParamStockDto {

    private BigDecimal width;
    private BigDecimal height;
    private int count;
}
