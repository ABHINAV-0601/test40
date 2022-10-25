package com.michaels.designhub.repository;

import com.michaels.designhub.entity.ColorPicker;
import com.michaels.designhub.repository.ColorPickerRepository;
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