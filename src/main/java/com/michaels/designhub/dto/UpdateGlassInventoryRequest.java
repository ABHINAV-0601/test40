package com.michaels.designhub.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author Baojian Hong
 * @Date 2022/9/16 9:11
 * @Version 1.0
 */
@Data
public class UpdateGlassInventoryRequest {
    private List<StoreGlassInventoryRequest> store_glass_inventory;
}
