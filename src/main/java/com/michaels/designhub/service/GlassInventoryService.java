package com.michaels.designhub.service;


import com.michaels.designhub.dto.UpdateGlassInventoryRequest;

/**
 * @Author Baojian Hong
 * @Date 2022/9/15 15:28
 * @Version 1.0
 */
public interface GlassInventoryService {
    Object get(String storeId);

    Object update(UpdateGlassInventoryRequest updateGlassInventoryRequest);
}
