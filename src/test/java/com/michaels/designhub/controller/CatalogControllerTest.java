package com.michaels.designhub.controller;

import com.michaels.designhub.entity.CatalogEntry;
import com.michaels.designhub.service.impl.CatalogServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatalogControllerTest {

    @Mock
    CatalogServiceImpl catalogService;

    @InjectMocks
    CatalogController catalogController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCatalog(){
        String resp = "";
        when(catalogService.getCatalog()).thenReturn(resp);
        String controlResp = catalogController.getCatalog();
        Assertions.assertEquals(resp, controlResp);
    }
}
