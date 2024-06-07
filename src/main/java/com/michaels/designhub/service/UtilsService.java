package com.michaels.designhub.service;

import com.michaels.designhub.dto.TrackingNumberDto;
import com.michaels.designhub.dto.UtilsDto;
import com.michaels.designhub.entity.TrainingLog;
import com.michaels.designhub.request.SearchGSOAndLayoutOptimizationRequest;
import com.michaels.designhub.response.SearchGSOAndLayoutOptimizationResponse;
import com.michaels.designhub.response.TrackingNumberResponse;

import javax.servlet.http.HttpServletRequest;
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
    public Map<String,Object> utils(UtilsDto utilsDto, HttpServletRequest httpServletRequest);

    /**
     * save training details
     * @param trainingLog
     * @return
     */
    Integer saveTrainingLog(TrainingLog trainingLog);

    /**
     * update training log exit timestamp
     * @param id
     */
    void exitTrainingLog(Integer id);


    TrackingNumberResponse updateTrackingNumbers(TrackingNumberDto trackingNumberDto);
}
