package com.michaels.designhub.response;

import lombok.Data;

import java.util.List;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:23
 * @Version 1.0
 */
@Data
public class SearchGSOAndLayoutOptimizationResponse {

    private String store_id;
    private List<PrintedLayout> printed_layout;
    private NonPrintedLayoutParent non_printed_layout;
}
