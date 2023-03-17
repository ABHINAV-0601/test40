package com.michaels.designhub.controller;

import com.michaels.designhub.dto.UpdateOfflinePINRequest;
import com.michaels.designhub.dto.UpdateOfflinePINResponse;
import com.michaels.designhub.entity.OfflinePIN;
import com.michaels.designhub.response.OfflinePINResponse;
import com.michaels.designhub.service.OfflinePINService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @Author Baojian Hong
 * @Date 2022/9/5 14:10
 * @Version 1.0
 */
@RestController
@RequestMapping("/v1")
@Validated
@Slf4j
public class OfflinePINController {
    @Autowired
    private OfflinePINService offlinePINService;

    @GetMapping("/offline-pin")
    public OfflinePINResponse getPin(@RequestParam(name = "store_id") @NotBlank(message = "store_id should not be null or blank") String storeId){
        OfflinePIN pin = offlinePINService.getPin(storeId);
        OfflinePINResponse offlinePINResponse = new OfflinePINResponse();
        BeanUtils.copyProperties(pin,offlinePINResponse);
        return offlinePINResponse;
    }

    @PatchMapping("/offline-pin")
    public UpdateOfflinePINResponse UpdatePin(@RequestBody UpdateOfflinePINRequest updateOfflinePINRequest){
        return offlinePINService.UpdatePin(updateOfflinePINRequest);

    }
}
