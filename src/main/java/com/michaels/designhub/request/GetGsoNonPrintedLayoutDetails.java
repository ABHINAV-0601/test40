package com.michaels.designhub.request;

import lombok.Data;

/**
 * @Author Baojian Hong
 * @Date 2022/9/3 16:14
 * @Version 1.0
 */
@Data
public class GetGsoNonPrintedLayoutDetails {
    private String glass_type;
    private GetGsoNonPrintedLayoutReqInfo req_info;
}
