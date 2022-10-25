package com.michaels.designhub.controller;

import com.michaels.designhub.ColorPickerApiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author Baojian Hong
 * @Date 2022/9/26 13:49
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ColorPickerApiApplication.class)
public class OfflinePINControllerTest {


    @Autowired
    OfflinePINController offlinePINController;

    @Test
    public void getPin() {
        String storeId = "1056";
        offlinePINController.getPin(storeId);
    }
}