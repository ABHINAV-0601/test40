package com.michaels.designhub.service.impl;

import com.michaels.designhub.repository.FramersRepository;
import com.michaels.designhub.dto.StoreGlassInventoryRequest;
import com.michaels.designhub.dto.UpdateGlassInventoryRequest;
import com.michaels.designhub.dto.FetchGlassInventoryResponse;
import com.michaels.designhub.service.GlassInventoryService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class GlassInventoryServiceImpl implements GlassInventoryService {

    public static final String STATUS_CODE = "status_code";
    public static final String STATUS_MESSAGE = "status_message";
    @Autowired
    private FramersRepository framersRepository;

    @Override
    public Object get(String storeId) {
        log.debug("get - get GlassInventory by {}.",storeId);
        List<Map<String, Object>> list = framersRepository.getGlassInventory(storeId);
        if (!list.isEmpty()) {
            FetchGlassInventoryResponse fetchGlassInventoryResponse = new FetchGlassInventoryResponse();
            fetchGlassInventoryResponse.setStore_id(storeId);
            fetchGlassInventoryResponse.setStore_glass_inventory(list);
            return fetchGlassInventoryResponse;
        } else {
            log.debug("get - No Inventory data found.");
            Map<String, Object> map = new HashMap<>();
            map.put("Message", "No Inventory data found");
            return map;
        }
    }

    @Override
    public Object update(UpdateGlassInventoryRequest updateGlassInventoryRequest) {
        log.debug("update - update GlassInventory params {}.",updateGlassInventoryRequest);
        Map<String, Object> map = new HashMap<>();
        map.put(STATUS_CODE, 1);
        if (null == updateGlassInventoryRequest || null == updateGlassInventoryRequest.getStore_glass_inventory() || updateGlassInventoryRequest.getStore_glass_inventory().isEmpty()) {
            log.debug("update GlassInventory params check Failed.");
            map.put(STATUS_CODE, 0);
            map.put(STATUS_MESSAGE, "Failed");
            return map;
        }
        List<StoreGlassInventoryRequest> store_glass_inventory = updateGlassInventoryRequest.getStore_glass_inventory();
        for (StoreGlassInventoryRequest storeGlassInventory : store_glass_inventory) {
            int i = framersRepository.update(storeGlassInventory.getStore_glass_inventory_id(),storeGlassInventory.getInventory_count());
            if (i == 0 && Integer.parseInt(map.get(STATUS_CODE).toString()) != 0){
                log.debug("update - update GlassInventory Failed.");
                map.put(STATUS_CODE, 0);
                map.put(STATUS_MESSAGE, "Failed");
            }else{
                map.put(STATUS_MESSAGE, "Success");
            }
        }
        return map;
    }
}
