package com.michaels.designhub.colorpickerapi.controller;
import com.michaels.designhub.colorpickerapi.common.dto.Result;
import com.michaels.designhub.colorpickerapi.service.IColorPickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Qiang Hu
 */
@Controller
@RequestMapping("/api/v1")
public class ColorPickerController {

    @Autowired
    private IColorPickerService colorPickerService;

    @ResponseBody
    @GetMapping("/colors")
    public Result getColor() {
        return Result.success(colorPickerService.findColorPickerByLocale());
    }
}