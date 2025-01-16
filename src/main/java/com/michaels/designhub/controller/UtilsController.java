package com.michaels.designhub.controller;

import com.michaels.designhub.dto.TrackingNumberDto;
import com.michaels.designhub.dto.UtilsDto;
import com.michaels.designhub.entity.TrainingLog;
import com.michaels.designhub.request.SearchGSOAndLayoutOptimizationRequest;
import com.michaels.designhub.response.SearchGSOAndLayoutOptimizationResponse;
import com.michaels.designhub.response.TrackingNumberResponse;
import com.michaels.designhub.service.impl.UtilsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:17
 * @Version 1.0
 */
@RestController
@RequestMapping("/v1")
@Slf4j
public class UtilsController {
    @Autowired
    private UtilsServiceImpl utilsService;

    @PostMapping("/utils/gso")
    public SearchGSOAndLayoutOptimizationResponse utilsGso(@RequestBody SearchGSOAndLayoutOptimizationRequest searchGSOAndLayoutOptimizationRequest) throws Exception {
        log.debug("In Search GSO and Optimization flow");
        log.debug(searchGSOAndLayoutOptimizationRequest.toString());
        return utilsService.utilsGso(searchGSOAndLayoutOptimizationRequest);
    }

    @PostMapping("/utils")
    public Map<String, Object> utils(@RequestBody UtilsDto utilsDto, HttpServletRequest httpServletRequest) {
        return utilsService.utils(utilsDto, httpServletRequest);
    }

    @PostMapping("/utils/training")
    public Integer logTraining(@RequestBody TrainingLog tlog) {
        return utilsService.saveTrainingLog(tlog);
    }

    @PutMapping("/utils/training")
    public void leaveTraining(@RequestBody Map<String, Integer> body) {
        utilsService.exitTrainingLog(body.get("id"));
    }

    @PostMapping("/utils/tracking-numbers")
    public TrackingNumberResponse trackingNumbers(@RequestBody TrackingNumberDto trackingNumberDto) {
        return utilsService.updateTrackingNumbers(trackingNumberDto);

    }

    @PostMapping("/utils/tracking-status-update")
    public TrackingNumberResponse trackingNumbersStatusUpdate(@RequestBody TrackingNumberDto trackingNumberDto) {
        return utilsService.updateComponentStatusForTrackingNumbers(trackingNumberDto);
    }

    // Generic method to encode HTML for all String fields in a DTO
    public static <T> T encodeDto(T dto) {
        // Get all fields of the DTO class
        Field[] fields = dto.getClass().getDeclaredFields();

        // Iterate over all fields
        for (Field field : fields) {
            // Make the field accessible if it's private
            field.setAccessible(true);

            try {
                // Check if the field is of type String
                if (field.get(dto) instanceof String) {
                    String value = (String) field.get(dto);
                    // Encode the String value for HTML
                    String encodedValue = Encode.forHtml(value);
                    // Set the encoded value back to the field
                    field.set(dto, encodedValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return dto;
    }
}
