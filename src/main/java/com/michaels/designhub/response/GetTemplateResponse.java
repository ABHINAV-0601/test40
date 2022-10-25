package com.michaels.designhub.response;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;

/**
 * @Author Baojian Hong
 * @Date 2022/8/26 15:49
 * @Version 1.0
 */
@Data
public class GetTemplateResponse {

    String url;

    private HashMap<String, Object> vision;

    private HashMap<String, String> customAttributes;

    String type;

    int id;

    Date createdAt;
}
