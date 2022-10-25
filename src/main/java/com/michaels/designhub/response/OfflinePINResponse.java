package com.michaels.designhub.response;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author Baojian Hong
 * @Date 2022/9/5 16:02
 * @Version 1.0
 */
@Data
public class OfflinePINResponse {

    private String encrypted_pin;

    private String hashed_pin;

    private LocalDateTime update_at;

    private LocalDateTime expiry_date;
}
