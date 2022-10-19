package com.michaels.designhub.colorpickerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: ColorPickerApiApplication
 * @Author: Qiang Hu
 * @Date: 9/1/2022 2:55 PM
 */
@SpringBootApplication(scanBasePackages = {"com.michaels.designhub"})
public class ColorPickerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ColorPickerApiApplication.class, args);
    }

}
