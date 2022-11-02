package com.michaels.designhub.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.michaels.designhub.request.SearchGSOAndLayoutOptimizationRequest;
import com.michaels.designhub.response.SearchGSOAndLayoutOptimizationResponse;
import com.michaels.designhub.service.impl.GSOServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

/**
 * @Author Baojian Hong
 * @Date 2022/10/26 11:27
 * @Version 1.0
 */
class GSOControllerTest {
    @Mock
    Logger logger;
    @Mock
    GSOServiceImpl gsoService;
    @InjectMocks
    GSOController gSOController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUtilsGso() throws Exception {
        when(gsoService.utilsGso(any())).thenReturn(new SearchGSOAndLayoutOptimizationResponse());

        SearchGSOAndLayoutOptimizationResponse result = gSOController.utilsGso(new SearchGSOAndLayoutOptimizationRequest());
        Assertions.assertEquals(new SearchGSOAndLayoutOptimizationResponse(), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme