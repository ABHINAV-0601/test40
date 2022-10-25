package com.michaels.designhub.controller;

import com.alibaba.fastjson.JSONObject;
import com.michaels.designhub.ColorPickerApiApplication;
import com.michaels.designhub.dto.UpdateGlassInventoryRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author Baojian Hong
 * @Date 2022/9/26 14:15
 * @Version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ColorPickerApiApplication.class)
public class GlassInventoryControllerTest {

    @Autowired
    private GlassInventoryController glassInventoryController;

    @Test
    public void get() {
        String storeId = "1056";
        glassInventoryController.get(storeId);
    }

    @Test
    public void update() {
        String json = "{\"store_glass_inventory\":[{\"store_glass_inventory_id\":844,\"inventory_count\":1000},{\"store_glass_inventory_id\":2121,\"inventory_count\":1000},{\"store_glass_inventory_id\":3398,\"inventory_count\":1000},{\"store_glass_inventory_id\":4675,\"inventory_count\":1000},{\"store_glass_inventory_id\":5952,\"inventory_count\":1000},{\"store_glass_inventory_id\":7229,\"inventory_count\":1000},{\"store_glass_inventory_id\":8506,\"inventory_count\":1000},{\"store_glass_inventory_id\":9783,\"inventory_count\":1000},{\"store_glass_inventory_id\":11060,\"inventory_count\":1000},{\"store_glass_inventory_id\":12337,\"inventory_count\":1000},{\"store_glass_inventory_id\":13614,\"inventory_count\":1000},{\"store_glass_inventory_id\":14891,\"inventory_count\":1000},{\"store_glass_inventory_id\":16168,\"inventory_count\":1000},{\"store_glass_inventory_id\":17445,\"inventory_count\":1000}]}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        UpdateGlassInventoryRequest updateGlassInventoryRequest = JSONObject.toJavaObject(jsonObject, UpdateGlassInventoryRequest.class);
        glassInventoryController.update(updateGlassInventoryRequest);
    }
}