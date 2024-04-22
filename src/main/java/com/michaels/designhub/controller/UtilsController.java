package com.michaels.designhub.controller;

import com.michaels.designhub.dto.TrackingNumberDto;
import com.michaels.designhub.dto.UtilsDto;
import com.michaels.designhub.entity.TrainingLog;
import com.michaels.designhub.request.SearchGSOAndLayoutOptimizationRequest;
import com.michaels.designhub.response.SearchGSOAndLayoutOptimizationResponse;
import com.michaels.designhub.response.TrackingNumberResponse;
import com.michaels.designhub.service.impl.UtilsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:17
 * @Version 1.0
 */
@RestController
@RequestMapping("/v1")
@Slf4j
public class UtilsController {
    @Autowired
    private UtilsServiceImpl utilsService;

    @PostMapping("/utils/gso")
    public SearchGSOAndLayoutOptimizationResponse utilsGso(@RequestBody SearchGSOAndLayoutOptimizationRequest searchGSOAndLayoutOptimizationRequest) throws Exception {
        log.debug("In Search GSO and Optimization flow");
        log.debug(searchGSOAndLayoutOptimizationRequest.toString());
        return utilsService.utilsGso(searchGSOAndLayoutOptimizationRequest);
    }

    @PostMapping("/utils")
    public Map<String,Object> utils(@RequestBody UtilsDto utilsDto, HttpServletRequest httpServletRequest) {
        log.debug("In Utils and calling function - {}, with function params - {}", utilsDto.getFunctionName(), utilsDto.getFunctionParams());
        return utilsService.utils(utilsDto,httpServletRequest);
    }

    @PostMapping("/utils/training")
    public Integer logTraining(@RequestBody TrainingLog tlog) {
        return utilsService.saveTrainingLog(tlog);
    }

    @PutMapping("/utils/training")
    public void leaveTraining(@RequestBody Map<String, Integer> body) {
        utilsService.exitTrainingLog(body.get("id"));
    }

    @PostMapping("/utils/tracking-numbers")
    public TrackingNumberResponse trackingNumbers(@RequestBody TrackingNumberDto trackingNumberDto){
        return utilsService.updateTrackingNumbers(trackingNumberDto);

    }
}
