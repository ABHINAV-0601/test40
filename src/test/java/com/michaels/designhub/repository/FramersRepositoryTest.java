package com.michaels.designhub.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FramersRepositoryTest {

    @Mock
    private FramersRepository framersRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void setFramersRepository(){
        List<Map<String, Object>> lisRepo = new ArrayList<Map<String, Object>>();
        when(framersRepository.getGlassInventory(anyString())).thenReturn(lisRepo);
        List<Map<String, Object>> repoList = framersRepository.getGlassInventory("1");
        Assertions.assertEquals(lisRepo, repoList);
    }

}
