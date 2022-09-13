package com.michaels.designhub.colorpickerapi.repository;

import com.michaels.designhub.colorpickerapi.entity.ColorPicker;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ColorPickerRepositoryTest {

    @Mock
    private ColorPickerRepository colorPickerRepository;

    @Test
    public void findColorPickerByLocale() {
        List<ColorPicker> list = colorPickerRepository.findColorPickerByLocale();
        Assert.assertNotNull(list);
    }
}