package com.michaels.designhub.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.michaels.designhub.request.SearchGSOAndLayoutOptimizationRequest;
import com.michaels.designhub.response.SearchGSOAndLayoutOptimizationResponse;
import com.michaels.designhub.service.impl.GSOServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:17
 * @Version 1.0
 */
@RestController
@RequestMapping("/v1")
public class GSOController {

    Logger logger = LoggerFactory.getLogger(GSOController.class);

    @Autowired
    private GSOServiceImpl gsoService;

    @PostMapping("/utils/gso")
    public SearchGSOAndLayoutOptimizationResponse utilsGso(@RequestBody SearchGSOAndLayoutOptimizationRequest searchGSOAndLayoutOptimizationRequest) throws JsonProcessingException, SQLException {
        logger.info("In Search GSO and Optimization flow");
        logger.info(searchGSOAndLayoutOptimizationRequest.toString());
        SearchGSOAndLayoutOptimizationResponse searchGSOAndLayoutOptimizationResponse = gsoService.utilsGso(searchGSOAndLayoutOptimizationRequest);
        return searchGSOAndLayoutOptimizationResponse;
    }
}
