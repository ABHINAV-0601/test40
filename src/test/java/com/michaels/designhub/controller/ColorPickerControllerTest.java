package com.michaels.designhub.controller;

import com.michaels.designhub.entity.ColorPicker;
import com.michaels.designhub.service.impl.ColorPickerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ColorPickerControllerTest {

    @InjectMocks
    private com.michaels.designhub.controller.ColorPickerController ColorPickerController;

    @Mock
    private ColorPickerService colorPickerService;

    @Test
    public void getColorsTest() {
        List<ColorPicker> list = new ArrayList<>();
        when(colorPickerService.findColorPickerByLocale()).thenReturn(list);
        Map<String,Object> result = ColorPickerController.getColor(null);
        Assert.assertNotNull(result);
    }
}