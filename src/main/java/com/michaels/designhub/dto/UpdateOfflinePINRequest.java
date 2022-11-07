package com.michaels.designhub.dto;

import lombok.Data;

/**
 * @Author Baojian Hong
 * @Date 2022/11/7 14:48
 * @Version 1.0
 */
@Data
public class UpdateOfflinePINRequest {
    private String store_id;
    private String encrypted_pin;
    private String hashed_pin;
    private String expiry_date;
}
