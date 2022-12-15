package com.michaels.designhub.controller;

import com.michaels.designhub.request.SearchGSOAndLayoutOptimizationRequest;
import com.michaels.designhub.response.SearchGSOAndLayoutOptimizationResponse;
import com.michaels.designhub.service.impl.UtilsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

/**
 * @Author Baojian Hong
 * @Date 2022/10/26 11:27
 * @Version 1.0
 */
class UtilsControllerTest {
    @Mock
    Logger logger;
    @Mock
    UtilsServiceImpl utilsService;
    @InjectMocks
    UtilsController utilsController;

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
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme