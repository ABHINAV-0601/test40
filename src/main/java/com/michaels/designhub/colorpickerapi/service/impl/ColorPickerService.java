package com.michaels.designhub.colorpickerapi.service.impl;

import com.michaels.designhub.colorpickerapi.entity.ColorPicker;
import com.michaels.designhub.colorpickerapi.repository.ColorPickerRepository;
import com.michaels.designhub.colorpickerapi.service.IColorPickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: ColorPickerService
 * @Author: Qiang Hu
 * @Date: 9/1/2022 2:56 PM
 */
@Service
public class ColorPickerService implements IColorPickerService {

    @Autowired
    private ColorPickerRepository colorPickerRepository;

    @Override
    public List<ColorPicker> findColorPickerByLocale() {
        return colorPickerRepository.findColorPickerByLocale();
    }
}
