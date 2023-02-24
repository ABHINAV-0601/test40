package com.michaels.designhub.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.michaels.designhub.entity.CatalogEntry;
import com.michaels.designhub.repository.CatalogRepository;
import com.michaels.designhub.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    CatalogRepository catalogRepository;

    /**
     * @return catalog details
     */
    @Override
    public String getCatalog() {
        List<CatalogEntry> response = catalogRepository.findAll();
        String resp = "{}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            resp = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            resp = "err";
            e.printStackTrace();
        }
        return resp;
    }
}
