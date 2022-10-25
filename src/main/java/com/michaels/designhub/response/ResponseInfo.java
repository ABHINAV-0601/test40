package com.michaels.designhub.response;

import lombok.Data;

import java.util.List;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:28
 * @Version 1.0
 */
@Data
public class ResponseInfo {

    private String cutGLib_version;
    private List<Layouts> layouts;
    private List<String> log;
    private List<UnoptimizedOrderParts> unoptimized_order_parts;
}
