package com.michaels.designhub.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author Baojian Hong
 * @Date 2022/9/15 15:31
 * @Version 1.0
 */
@Data
public class FetchGlassInventoryResponse {
    private String store_id;
    private List<Map<String,Object>> store_glass_inventory;
}
