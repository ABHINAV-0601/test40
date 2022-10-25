package com.michaels.designhub.response;

import lombok.Data;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:30
 * @Version 1.0
 */
@Data
public class OrderParts {
    private int number;
    private int stock;
    private int width;
    private int height;
    private String orderId;
    private int aX;
    private int aY;
    private boolean rotated;
}
