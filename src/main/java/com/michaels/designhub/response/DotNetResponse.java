package com.michaels.designhub.response;

import com.michaels.designhub.request.orderParts;
import lombok.Data;

import java.util.List;

/**
 * @Author Baojian Hong
 * @Date 2022/9/3 14:07
 * @Version 1.0
 */
@Data
public class DotNetResponse {
    private String cutGLib_version;
    private List<Layouts> layouts;
    private List<orderParts> unoptimized_order_parts;
}
