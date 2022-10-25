package com.michaels.designhub.request;

import lombok.Data;

/**
 * @Author Baojian Hong
 * @Date 2022/9/8 16:29
 * @Version 1.0
 */
@Data
public class EmailNotificationRequest {
    private Integer order_lineitem_id;
    private String order_lineitem_number;
}
