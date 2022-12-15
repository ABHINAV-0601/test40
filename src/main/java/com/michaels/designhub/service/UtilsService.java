package com.michaels.designhub.service;

import com.michaels.designhub.dto.UtilsDto;
import com.michaels.designhub.request.SearchGSOAndLayoutOptimizationRequest;
import com.michaels.designhub.response.SearchGSOAndLayoutOptimizationResponse;

import java.util.Map;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:54
 * @Version 1.0
 */
public interface UtilsService {

    public SearchGSOAndLayoutOptimizationResponse utilsGso(SearchGSOAndLayoutOptimizationRequest searchGSOAndLayoutOptimizationRequest) throws Exception;

    /**
     * call function
     * @param utilsDto
     * @return
     */
    public Map<String,Object> utils(UtilsDto utilsDto);
}
