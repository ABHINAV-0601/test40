package com.michaels.designhub.controller;

import com.michaels.designhub.dto.UtilsDto;
import com.michaels.designhub.entity.TrainingLog;
import com.michaels.designhub.request.SearchGSOAndLayoutOptimizationRequest;
import com.michaels.designhub.response.SearchGSOAndLayoutOptimizationResponse;
import com.michaels.designhub.service.impl.UtilsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * @Author Baojian Hong
 * @Date 2022/10/26 11:27
 * @Version 1.0
 */
@ExtendWith(MockitoExtension.class)
class UtilsControllerTest {
    @Mock
    Logger logger;
    @Mock
    UtilsServiceImpl utilsService;
    @InjectMocks
    UtilsController utilsController;

    @Mock
    private List<Integer> mockList;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUtilsGso() throws Exception {
        when(utilsService.utilsGso(any())).thenReturn(new SearchGSOAndLayoutOptimizationResponse());
        SearchGSOAndLayoutOptimizationResponse result = utilsController.utilsGso(new SearchGSOAndLayoutOptimizationRequest());
        Assertions.assertEquals(new SearchGSOAndLayoutOptimizationResponse(), result);
    }

    @Test
    void testUtils(){
        Map<String,Object> map = new HashMap<>();
        UtilsDto utilsDto = new UtilsDto();
        utilsDto.setIsFunction(true);
        utilsDto.setFunctionName("get_rep_workload_planner");
        utilsDto.setFunctionParams("'1010','2022-12-25','2022-12-31'");
        when(utilsService.utils(any(),any(HttpServletRequest.class))).thenReturn(map);
        Map<String,Object> result = utilsController.utils(utilsDto,request);
        Assertions.assertNotNull(result);
    }

    @Test
    void testLogTraining(){
        TrainingLog trainingLog = new TrainingLog();
        trainingLog.setId(1);
        when(utilsService.saveTrainingLog(any())).thenReturn(1);
        Integer number = utilsService.saveTrainingLog(trainingLog);
        Assertions.assertEquals(number, 1);
    }

}
