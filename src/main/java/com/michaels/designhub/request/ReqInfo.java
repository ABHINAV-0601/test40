package com.michaels.designhub.request;

import lombok.Data;

import java.util.List;

/**
 * @Author Baojian Hong
 * @Date 2022/9/1 15:16
 * @Version 1.0
 */
@Data
public class ReqInfo {
    private List<orderParts> order_parts;
    private List<Stocks> stocks;
}
