package com.michaels.designhub.controller;

import com.michaels.designhub.entity.ColorPicker;
import com.michaels.designhub.service.IColorPickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public Map<String,Object> getColor(String locale) {
        List<ColorPicker> list = colorPickerService.findColorPickerByLocale();
        Map<String,Object> map = new HashMap<>();
        List<Map<String,Object>> colorList = new ArrayList<>();
        for(ColorPicker cp:list){
            Map<String,Object> color = new HashMap<>();
            color.put("id",cp.getId());
            color.put("value",cp.getValue());
            color.put("rgb_value",cp.getRbgValue());
            if(StringUtils.hasLength(locale) && locale.equals("en_US")){
                color.put("name",cp.getNameEn());
            }else{
                color.put("name",cp.getNameFr());
            }
            colorList.add(color);
        }
        map.put("color_family",colorList);
        return map;
    }
}
