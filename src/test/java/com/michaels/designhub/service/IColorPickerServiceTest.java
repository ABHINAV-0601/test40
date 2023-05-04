package com.michaels.designhub.service;

import com.michaels.designhub.entity.ColorPicker;
import com.michaels.designhub.repository.ColorPickerRepository;
import com.michaels.designhub.service.impl.ColorPickerService;
import org.junit.Assert;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IColorPickerServiceTest {

    @InjectMocks
    private ColorPickerService colorPickerService;

    @Mock
    private ColorPickerRepository colorPickerRepository;

    @Test
    public void findColorPickerByLocale() {
        when(colorPickerRepository.findColorPickerByLocale()).thenReturn(new ArrayList<>());
        List<ColorPicker> list = colorPickerService.findColorPickerByLocale();
        Assertions.assertNotNull(list);
    }
}