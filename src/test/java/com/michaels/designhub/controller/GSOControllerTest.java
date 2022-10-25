package com.michaels.designhub.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.michaels.designhub.ColorPickerApiApplication;;
import com.michaels.designhub.request.SearchGSOAndLayoutOptimizationRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
/**
 * @Author Baojian Hong
 * @Date 2022/9/26 14:11
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ColorPickerApiApplication.class)
public class GSOControllerTest {

    @Autowired
    private GSOController gsoController;

    @Test
    public void utilsGso() throws SQLException, JsonProcessingException {
        String json = "{\"store_id\":\"1054\",\"locale\":\"en-us\",\"order_lineitem_ids\":[1187192,1189014,1187320,1198492,1197701,1198493,1205862,1205948,1201086,1206016,1211280,1212745]}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        SearchGSOAndLayoutOptimizationRequest searchGSOAndLayoutOptimizationRequest = JSONObject.toJavaObject(jsonObject, SearchGSOAndLayoutOptimizationRequest.class);
        gsoController.utilsGso(searchGSOAndLayoutOptimizationRequest);
    }
}