package com.michaels.designhub.controller;
import com.michaels.designhub.service.IColorPickerService;
import com.michaels.designhub.common.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Qiang Hu
 */
@Controller
@RequestMapping("/v1")
public class ColorPickerController {

    @Autowired
    private IColorPickerService colorPickerService;

    @ResponseBody
    @GetMapping("/colors")
    public Result<Object> getColor() {
        return Result.success(colorPickerService.findColorPickerByLocale());
    }
}