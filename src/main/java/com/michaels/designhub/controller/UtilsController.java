package com.michaels.designhub.controller;

import com.alibaba.fastjson.JSONObject;
import com.michaels.designhub.dto.UtilsDto;
import com.michaels.designhub.request.SearchGSOAndLayoutOptimizationRequest;
import com.michaels.designhub.response.SearchGSOAndLayoutOptimizationResponse;
import com.michaels.designhub.service.TrainingService;
import com.michaels.designhub.service.impl.UtilsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private TrainingService trainingService;

    @PostMapping("/utils/gso")
    public SearchGSOAndLayoutOptimizationResponse utilsGso(@RequestBody SearchGSOAndLayoutOptimizationRequest searchGSOAndLayoutOptimizationRequest) throws Exception {
        log.info("In Search GSO and Optimization flow");
        log.info(searchGSOAndLayoutOptimizationRequest.toString());
        return utilsService.utilsGso(searchGSOAndLayoutOptimizationRequest);
    }

    @PostMapping("/utils")
    public Map<String,Object> utils(@RequestBody UtilsDto utilsDto) {
        log.info("In Utils and calling function - {}, with function params - {}", utilsDto.getFunctionName(), utilsDto.getFunctionParams());
        return utilsService.utils(utilsDto);
    }

    /** If a user selects training mode in UI,
     *  if checkbox is enabled, [method : saveTrainingLog]
     *      request     : firstname, lastname, role and store id.
     *      response    : id
     *  else If checkbox is disabled, [method : updateTrainingLog}
     *      request     : id
     *      response    : success/failure
     */

    @ResponseBody
    @PostMapping("/utils/training")
    public Object trainingPOST(@RequestBody JSONObject trainingObject) {
        return trainingService.saveTrainingLog(trainingObject);
    }

    @PutMapping("/utils/training")
    public void trainingPUT(@RequestBody JSONObject trainingPutObject) {
        trainingService.updateTrainingLog(trainingPutObject);
    }
}
