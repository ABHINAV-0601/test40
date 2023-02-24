package com.michaels.designhub.controller;

import com.michaels.designhub.service.CatalogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Venkatreddy
 * @Date 2022/8/31 14:17
 * @Version 1.0
 */
@RestController
@RequestMapping("/v1")
@Slf4j
public class CatalogController {

    @Autowired
    CatalogService catalogService;

    @GetMapping("/catalog/catalogs")
    public String getCatalog() {
        return catalogService.getCatalog();
    }

}
