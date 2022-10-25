package com.michaels.designhub.response;

import lombok.Data;

import java.util.List;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:27
 * @Version 1.0
 */
@Data
public class NonPrintedLayoutParent {
    private String store_id;
    private List<NonPrintedLayout> non_printed_layout;
}
