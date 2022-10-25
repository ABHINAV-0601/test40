package com.michaels.designhub.controller;

import com.michaels.designhub.common.dto.Result;
import com.michaels.designhub.service.impl.ColorPickerService;
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
    private com.michaels.designhub.controller.ColorPickerController ColorPickerController;

    @Mock
    private ColorPickerService colorPickerService;

    @Test
    public void getColorsTest() {
        when(colorPickerService.findColorPickerByLocale()).thenReturn(null);
        Result result = ColorPickerController.getColor();
        Assert.assertNotNull(result);
    }
}