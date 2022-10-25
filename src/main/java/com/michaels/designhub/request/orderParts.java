package com.michaels.designhub.request;

import lombok.Data;

/**
 * @Author Baojian Hong
 * @Date 2022/9/1 15:16
 * @Version 1.0
 */
@Data
public class orderParts {
    private Integer quantity;
    private String orderId;
    private Double width;
    private Double height;
}
