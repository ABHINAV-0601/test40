package com.michaels.designhub.controller;

import com.michaels.designhub.dto.UpdateGlassInventoryRequest;
import com.michaels.designhub.service.GlassInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Baojian Hong
 * @Date 2022/9/15 15:19
 * @Version 1.0
 */
@RestController
@RequestMapping("/v1")
public class GlassInventoryController {

    @Autowired
    private GlassInventoryService glassInventoryService;

    @GetMapping("/glass-inventory")
    public Object get(@RequestParam(name = "store_id") String storeId){
        return glassInventoryService.get(storeId);
    }

    @PatchMapping("/glass-inventory")
    public Object update(@RequestBody UpdateGlassInventoryRequest updateGlassInventoryRequest){
        return glassInventoryService.update(updateGlassInventoryRequest);
    }
}
