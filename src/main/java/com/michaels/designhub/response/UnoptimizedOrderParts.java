package com.michaels.designhub.response;

import lombok.Data;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:31
 * @Version 1.0
 */
@Data
public class UnoptimizedOrderParts {
    private int width;
    private int height;
    private String orderId;
}
