package com.michaels.designhub.response;

import lombok.Data;

import java.util.List;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:29
 * @Version 1.0
 */
@Data
public class Sheets {
    private int number;
    private int width;
    private int height;
    private int count;
    private List<OrderParts> order_parts;
    private List<String> waste_parts;

}
