package com.michaels.designhub.colorpickerapi.controller;

import com.michaels.designhub.colorpickerapi.common.dto.Result;
import com.michaels.designhub.colorpickerapi.service.impl.ColorPickerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ColorPickerControllerTest {

    @InjectMocks
    private ColorPickerController ColorPickerController;

    @Mock
    private ColorPickerService colorPickerService;

    @Test
    public void getColorsTest() {
        when(colorPickerService.findColorPickerByLocale()).thenReturn(null);
        Result result = ColorPickerController.getColor();
        Assert.assertNotNull(result);
    }
}