package com.michaels.designhub.service.impl;

import com.michaels.designhub.entity.ColorPicker;
import com.michaels.designhub.repository.ColorPickerRepository;
import com.michaels.designhub.service.IColorPickerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: ColorPickerService
 * @Author: Qiang Hu
 * @Date: 9/1/2022 2:56 PM
 */
@Service
@Slf4j
public class ColorPickerService implements IColorPickerService {

    @Autowired
    private ColorPickerRepository colorPickerRepository;

    @Override
    public List<ColorPicker> findColorPickerByLocale() {
        log.debug("ColorPicker findColorPickerByLocale.");
        return colorPickerRepository.findColorPickerByLocale();
    }
}
