package com.michaels.designhub.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @Author Baojian Hong
 * @Date 2022/9/5 15:48
 * @Version 1.0
 */

@Entity
@Data
@Table(name = "store_offline_login")
public class OfflinePIN {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    // select encrypted_pin,hashed_pin, expiry_date,update_at from store_offline_login where store_id = :STORE_ID LIMIT 1
    @Column(name = "encrypted_pin")
    private String encrypted_pin;

    @Column(name = "hashed_pin")
    private String hashed_pin;

    @Column(name = "update_at")
    private LocalDateTime update_at;

    @Column(name = "expiry_date")
    private LocalDateTime expiry_date;

}
