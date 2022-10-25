package com.michaels.designhub.repository;

import com.michaels.designhub.entity.ColorPicker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: ColorPickerRepository
 * @Author: Qiang Hu
 * @Date: 9/1/2022 2:49 PM
 */
@Repository
public interface ColorPickerRepository extends JpaRepository<ColorPicker,Integer> {

    @Query(value = "SELECT * FROM color_picker",nativeQuery = true)
    List<ColorPicker> findColorPickerByLocale();
}
