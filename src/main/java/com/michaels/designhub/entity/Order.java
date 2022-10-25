package com.michaels.designhub.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;

@Entity
//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
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

//    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
//    private List<OrderLineitem> order_lineitems = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime created_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Long getStore_id() {
        return store_id;
    }

    public void setStore_id(Long store_id) {
        this.store_id = store_id;
    }

    public String getFramer_username() {
        return framer_username;
    }

    public void setFramer_username(String framer_username) {
        this.framer_username = framer_username;
    }


    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getSource_app() {
        return source_app;
    }

    public void setSource_app(String source_app) {
        this.source_app = source_app;
    }

    public HashMap<String, Object> getCustom_attributes() {
        return custom_attributes;
    }

    public void setCustom_attributes(HashMap<String, Object> custom_attributes) {
        this.custom_attributes = custom_attributes;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }
//
//    public List<OrderLineitem> getOrder_lineitems() {
//        return order_lineitems;
//    }
//
//    public void setOrder_lineitems(List<OrderLineitem> order_lineitems) {
//        this.order_lineitems = order_lineitems;
//    }
}