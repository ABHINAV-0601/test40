package com.michaels.designhub.dto;

import lombok.Data;

/**
 * @Author Baojian Hong
 * @Date 2022/9/15 15:31
 * @Version 1.0
 */
@Data
public class StoreGlassInventoryResponse {
    private String store_glass_inventory_id;
    private String sku;
    private Integer inventory_count;
    private Integer short_side;
    private Integer long_side;
}
