package com.michaels.designhub.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:21
 * @Version 1.0
 */
@Data
public class SearchGSOAndLayoutOptimizationRequest implements Serializable {
    private String store_id;
    private String locale;
    private List<Integer> order_lineitem_ids;
}
