package com.michaels.designhub.service;


import com.michaels.designhub.entity.Framer;
import com.michaels.designhub.repository.FramersRepository;
import com.michaels.designhub.request.FramersRequest;
import com.michaels.designhub.service.impl.FramersServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class FramersServiceTest {

    @InjectMocks
    private FramersServiceImpl framersService;

    @Mock
    private FramersRepository framersRepository;

    @Test
    public void testGetFramersByUsername_FramerExists() {
        String testUsername = "testUser";
        Framer expectedFramer = new Framer();
        expectedFramer.setUsername(testUsername);
        when(framersRepository.getFramersByUsername(testUsername)).thenReturn(expectedFramer);
        Object result = framersService.getFramersByUsername(testUsername);

        assertTrue(result instanceof Framer);
        assertEquals(expectedFramer, result);
    }

    @Test
    public void testGetFramersByUsername_FramerNotFound() {
        String testUsername = "testUser";
        when(framersRepository.getFramersByUsername(testUsername)).thenReturn(null);
        Object result = framersService.getFramersByUsername(testUsername);
        assertTrue(result instanceof Map);
        Map<String, String> resultMap = (Map<String, String>) result;

        assertTrue(resultMap.containsKey("Message"));
        assertEquals("No Framer Data Found", resultMap.get("Message"));
    }

    @Test
    void testPostFramers_Success() {
        FramersRequest request = new FramersRequest();
        request.setFirstname("John");
        request.setLastname("Doe");
        request.setUpdated_at("2023-04-03");
        request.setUsername("johndoe");

        when(framersRepository.updateFramers(request.getUpdated_at(), request.getUsername())).thenReturn(1);

        Object result = framersService.postFramers(request);

        verify(framersRepository, never()).addFramers(anyString(), anyString(), anyString(), anyString());

        assertEquals(HashMap.class, result.getClass());
        Map<String, Object> resultMap = (Map<String, Object>) result;
        assertEquals(1, resultMap.get("status_code"));
        assertEquals("Success", resultMap.get("status_message"));
    }

    @Test
    void testPostFramers_Add() {
        FramersRequest request = new FramersRequest();
        request.setFirstname("xxxxxx");
        request.setLastname("yyyyy");
        request.setUpdated_at("2023-04-03");
        request.setUsername("xxyyxty");

        when(framersRepository.updateFramers(request.getUpdated_at(), request.getUsername())).thenReturn(0);
        when(framersRepository.addFramers(request.getFirstname(), request.getLastname(), request.getUpdated_at(), request.getUsername())).thenReturn(1);

        Object result = framersService.postFramers(request);

        assertEquals(HashMap.class, result.getClass());
        Map<String, Object> resultMap = (Map<String, Object>) result;
        assertEquals(1, resultMap.get("status_code"));
        assertEquals("Success", resultMap.get("status_message"));
    }

    @Test
    void testPostFramers_Failure() {
        FramersRequest request = new FramersRequest();
        request.setFirstname("xxxxxx");
        request.setLastname("yyyyy");
        request.setUpdated_at("2023-04-03");
        request.setUsername("xxyyxty");

        when(framersRepository.updateFramers(request.getUpdated_at(), request.getUsername())).thenReturn(0);
        when(framersRepository.addFramers(request.getFirstname(), request.getLastname(), request.getUpdated_at(), request.getUsername())).thenReturn(0);

        Object result = framersService.postFramers(request);

        assertEquals(HashMap.class, result.getClass());
        Map<String, Object> resultMap = (Map<String, Object>) result;
        assertEquals(0, resultMap.get("status_code"));
        assertEquals("Failure", resultMap.get("status_message"));
    }

}
