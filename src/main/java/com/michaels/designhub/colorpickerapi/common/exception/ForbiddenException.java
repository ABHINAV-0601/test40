package com.michaels.designhub.colorpickerapi.common.exception;

/**
 * @Description: ForbiddenException
 * @Author: Qiang Hu
 * @Date: 9/2/2022 5:18 PM
 */
public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}