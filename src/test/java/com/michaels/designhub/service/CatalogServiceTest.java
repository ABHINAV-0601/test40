package com.michaels.designhub.service;

import com.michaels.designhub.controller.CatalogController;
import com.michaels.designhub.entity.CatalogEntry;
import com.michaels.designhub.repository.CatalogRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CatalogServiceTest {
    @Mock
    private CatalogService catalogService;
    @InjectMocks
    CatalogController catalogController;

    @Test
    public void testGetCatalog(){
        String resp = "";
        when(catalogService.getCatalog()).thenReturn(resp);
        String controlResp = catalogController.getCatalog();
        Assertions.assertEquals(resp, controlResp);
    }
}
