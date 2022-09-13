package com.michaels.designhub.colorpickerapi.service;

import com.michaels.designhub.colorpickerapi.entity.ColorPicker;

import java.util.List;

/**
 * @Description: ColorPickerService
 * @Author: Qiang Hu
 * @Date: 9/1/2022 2:55 PM
 */
public interface IColorPickerService {

    List<ColorPicker> findColorPickerByLocale();
}
