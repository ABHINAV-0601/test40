package com.michaels.designhub.response;

import lombok.Data;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:32
 * @Version 1.0
 */
@Data
public class GlassDetails {
    private int glass_qty;
    private int glass_width;
    private int glass_height;
    private String order_lineitem_number;
    private int order_width;
    private int order_height;
    private String glass_bin;
}
