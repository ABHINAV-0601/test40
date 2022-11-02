package com.michaels.designhub.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.michaels.designhub.dto.UtilsDto;
import com.michaels.designhub.request.SearchGSOAndLayoutOptimizationRequest;
import com.michaels.designhub.response.SearchGSOAndLayoutOptimizationResponse;
import com.michaels.designhub.service.impl.GSOServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Map;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:17
 * @Version 1.0
 */
@RestController
@RequestMapping("/v1")
@Slf4j
public class GSOController {
    @Autowired
    private GSOServiceImpl gsoService;

    @PostMapping("/utils/gso")
    public SearchGSOAndLayoutOptimizationResponse utilsGso(@RequestBody SearchGSOAndLayoutOptimizationRequest searchGSOAndLayoutOptimizationRequest) throws Exception {
        log.info("In Search GSO and Optimization flow");
        log.info(searchGSOAndLayoutOptimizationRequest.toString());
        SearchGSOAndLayoutOptimizationResponse searchGSOAndLayoutOptimizationResponse = gsoService.utilsGso(searchGSOAndLayoutOptimizationRequest);
        return searchGSOAndLayoutOptimizationResponse;
    }

    @PostMapping("/utils")
    public Map<String,Object> utils(@RequestBody UtilsDto utilsDto) {
        log.info("In Search GSO and Optimization flow");
        Map<String,Object> result = gsoService.utils(utilsDto);
        return result;
    }
}
