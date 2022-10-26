package com.michaels.designhub.dto;

import lombok.Data;

import java.util.List;

/**
 * @Description: UtilsParamsDto
 * @Author: Qiang Hu
 * @Date: 10/26/2022 9:29 AM
 */
@Data
public class UtilsParamsDto {
    private List<UtilsParamStockDto> stocks;
    private List<UtilsParamPartDto> orderParts;
}
