package com.michaels.designhub.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author Baojian Hong
 * @Date 2022/11/7 14:48
 * @Version 1.0
 */
@Data
public class UpdateOfflinePINRequest {

    @NotBlank(message = "store_id should not be null or blank")
    private String store_id;

    @NotBlank(message = "encrypted_pin should not be null or blank")
    private String encrypted_pin;

    @NotBlank(message = "hashed_pin should not be null or blank")
    private String hashed_pin;

    @NotBlank(message = "expiry_date should not be null or blank")
    private String expiry_date;
}
