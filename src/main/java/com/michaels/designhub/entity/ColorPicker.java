package com.michaels.designhub.entity;

import lombok.Data;
import javax.persistence.*;

/**
 * @Description: ColorPicker
 * @Author: Qiang Hu
 * @Date: 9/1/2022 2:36 PM
 */
@Data
@Entity
@Table(name = "color_picker", schema = "public")
public class ColorPicker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "value")
    private String value;

    @Column(name = "rgb_value")
    private String rbgValue;

    @Column(name = "name_fr")
    private String nameFr;
}
