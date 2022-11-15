package com.michaels.designhub.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;

@Entity
@Data
@Table(name = "\"order\"")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "order_number", nullable = false, length = 50)
    private String order_number;

    @Column(name = "customer_id", nullable = false, length = 50)
    private Long customer_id;

    @Column(name = "store_id", nullable = false, length = 50)
    private Long store_id;

    @Column(name = "framer_username", nullable = false, length = 50)
    private String framer_username;

    @Column(name = "app_version", nullable = false, length = 50)
    private String app_version;

    @Column(name = "source_app", nullable = false)
    private String source_app;

    @Type(type = "jsonb")
    @Column(name = "custom_attributes", columnDefinition = "jsonb")
    private HashMap<String, Object> custom_attributes;
    @Column(name = "created_at")
    private LocalDateTime created_at;
}