package com.michaels.designhub.colorpickerapi.common.exception;

/**
 *
 * @Description: Business Exception
 * @Author: Qiang Hu
 * @Date: 9/2/2022 5:18 PM
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}