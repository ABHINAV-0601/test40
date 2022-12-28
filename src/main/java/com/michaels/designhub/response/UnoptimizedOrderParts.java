package com.michaels.designhub.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:31
 * @Version 1.0
 */
@Data
public class UnoptimizedOrderParts {
    private BigDecimal width;
    private BigDecimal height;
    private String orderId;
    private Integer quantity;
}
