package com.michaels.designhub.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "wd_store")
public class Store {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "store_name")
    private String storeName;

    @Id
    @Column(name = "store_number")
    private String storeNumber;

    @Column(name = "store_manager")
    private Long storeManager;

    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "district_code")
    private String districtCode;

    @Column(name = "region_code")
    private String regionCode;

    @Column(name = "division_code")
    private String divisionCode;

    @Column(name = "market")
    private String market;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address_street")
    private String addressStreet;

    @Column(name = "address_city")
    private String addressCity;

    @Column(name = "address_state")
    private String addressState;

    @Column(name = "address_zip")
    private String addressZip;

    @Column(name = "address_country")
    private String addressCountry;

    @Column(name = "is_active")
    private boolean isActive;

}
