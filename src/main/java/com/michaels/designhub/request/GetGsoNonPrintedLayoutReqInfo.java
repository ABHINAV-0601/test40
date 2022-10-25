package com.michaels.designhub.request;

import lombok.Data;

import java.util.List;

/**
 * @Author Baojian Hong
 * @Date 2022/9/3 16:15
 * @Version 1.0
 */
@Data
public class GetGsoNonPrintedLayoutReqInfo {
    private List<orderParts> unoptimized_order_parts;
}
