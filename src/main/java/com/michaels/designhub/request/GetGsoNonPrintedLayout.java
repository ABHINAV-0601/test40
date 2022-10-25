package com.michaels.designhub.request;

import lombok.Data;

import java.util.List;

/**
 * @Author Baojian Hong
 * @Date 2022/9/3 16:02
 * @Version 1.0
 */
@Data
public class GetGsoNonPrintedLayout {
    private String store_id;
    private String locale;
    private List<GetGsoNonPrintedLayoutDetails> details;
}
