package com.michaels.designhub.controller;

import com.michaels.designhub.entity.ColorPicker;
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
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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
        Assertions.assertNotNull(result);
    }
}