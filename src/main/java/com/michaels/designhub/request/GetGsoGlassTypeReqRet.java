package com.michaels.designhub.request;

import lombok.Data;

import java.util.List;

/**
 * @Author Baojian Hong
 * @Date 2022/9/1 15:05
 * @Version 1.0
 */
@Data
public class GetGsoGlassTypeReqRet {
    private String store_id;
    private List<Details> details;
}
