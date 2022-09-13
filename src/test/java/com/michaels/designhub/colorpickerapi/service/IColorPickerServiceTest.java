package com.michaels.designhub.colorpickerapi.service;

import com.michaels.designhub.colorpickerapi.entity.ColorPicker;
import com.michaels.designhub.colorpickerapi.repository.ColorPickerRepository;
import com.michaels.designhub.colorpickerapi.service.impl.ColorPickerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IColorPickerServiceTest {

    @InjectMocks
    private ColorPickerService colorPickerService;

    @Mock
    private ColorPickerRepository colorPickerRepository;

    @Test
    public void findColorPickerByLocale() {
        when(colorPickerRepository.findColorPickerByLocale()).thenReturn(new ArrayList<>());
        List<ColorPicker> list = colorPickerService.findColorPickerByLocale();
        Assert.assertNotNull(list);
    }
}