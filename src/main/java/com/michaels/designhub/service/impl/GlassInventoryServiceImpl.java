package com.michaels.designhub.service.impl;

import com.michaels.designhub.repository.FramersRepository;
import com.michaels.designhub.dto.StoreGlassInventoryRequest;
import com.michaels.designhub.dto.UpdateGlassInventoryRequest;
import com.michaels.designhub.dto.FetchGlassInventoryResponse;
import com.michaels.designhub.service.GlassInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Baojian Hong
 * @Date 2022/9/15 15:27
 * @Version 1.0
 */
@Service
public class GlassInventoryServiceImpl implements GlassInventoryService {

    @Autowired
    private FramersRepository framersRepository;

    @Override
    public Object get(String storeId) {

        List<Map<String, Object>> list = framersRepository.getGlassInventory(storeId);
        if (!list.isEmpty() || list.size() != 0) {
            FetchGlassInventoryResponse fetchGlassInventoryResponse = new FetchGlassInventoryResponse();
            fetchGlassInventoryResponse.setStore_id(storeId);
            fetchGlassInventoryResponse.setStore_glass_inventory(list);
            return fetchGlassInventoryResponse;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("Message", "No Inventory data found");
            return map;
        }
    }

    @Override
    public Object update(UpdateGlassInventoryRequest updateGlassInventoryRequest) {
        Map<String, Object> map = new HashMap<>();
        map.put("status_code", 1);
        if (null == updateGlassInventoryRequest || null == updateGlassInventoryRequest.getStore_glass_inventory() || updateGlassInventoryRequest.getStore_glass_inventory().size() == 0) {
            map.put("status_code", 0);
            map.put("status_message", "Failed");
            return map;
        }

        List<StoreGlassInventoryRequest> store_glass_inventory = updateGlassInventoryRequest.getStore_glass_inventory();
        for (StoreGlassInventoryRequest storeGlassInventory : store_glass_inventory) {
            int i = framersRepository.update(storeGlassInventory.getStore_glass_inventory_id(),storeGlassInventory.getInventory_count());
            if (i == 0 && Integer.parseInt(map.get("status_code").toString()) != 0){
                map.put("status_code", 0);
                map.put("status_message", "Failed");
            }else{
                map.put("status_message", "Success");
            }
        }
        return map;
    }
}
