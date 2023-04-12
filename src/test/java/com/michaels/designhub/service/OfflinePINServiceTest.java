package com.michaels.designhub.service;

import com.michaels.designhub.entity.OfflinePIN;
import com.michaels.designhub.repository.OfflinePINRepository;
import com.michaels.designhub.service.impl.OfflinePINServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OfflinePINServiceTest {

    @Mock
    private OfflinePINServiceImpl offlinePINService;

    @Mock
    private OfflinePINRepository offlinePINRepositoryMock;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPin() {
        String storeId = "123";
        OfflinePIN expected = new OfflinePIN();
        expected.setId(Long.valueOf(storeId));
        expected.setEncrypted_pin("1234");
        when(offlinePINRepositoryMock.selectByStoreId(storeId)).thenReturn(List.of(expected));
        assertEquals(expected, offlinePINService.getPin(storeId));
    }
}
