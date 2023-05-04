package com.michaels.designhub.repository;

import com.michaels.designhub.entity.CatalogEntry;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CatalogRepositoryTest {
    @Mock
    private CatalogRepository catalogRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void setCatalogRepository(){
        List<CatalogEntry> response =  catalogRepository.findAll();
        Assert.assertNotNull(response);
    }

}
