package com.michaels.designhub.response;

import lombok.Data;

import java.util.List;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:29
 * @Version 1.0
 */
@Data
public class Layouts {
    private int number;
    private List<Sheets> sheets;
}
