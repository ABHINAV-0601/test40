package com.michaels.designhub.colorpickerapi.common.enums;

import com.michaels.designhub.colorpickerapi.common.service.IResult;


/**
 * response code info
 * 200X success
 * 300X check info
 * 400X execute error
 * 500X system error
 *
 * @author Qiang Hu
 */

public enum ResultEnum implements IResult {
    SUCCESS(2001, "Success"),
    VALIDATE_FAILED(3001, "Parameter verification fails."),
    FORBIDDEN(3002, "No permission to access the resource."),
    COMMON_FAILED(4001, "Interface invocation failed."),

    SYSTEM_ERROR(5001,"System error");

    private Integer code;
    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultEnum valueOf(int code) {
        for (ResultEnum resultEnum : values()) {
            if (resultEnum.getCode() == code) {
                return resultEnum;
            }
        }
        throw new IllegalArgumentException("ResultEnum no matching enums for [" + code + "]");
    }
}