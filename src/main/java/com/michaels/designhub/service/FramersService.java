package com.michaels.designhub.service;

import com.michaels.designhub.request.FramersRequest;

/**
 * @Author Baojian Hong
 * @Date 2022/9/13 10:11
 * @Version 1.0
 */
public interface FramersService {
    Object getFramersByUsername(String username);

    Object postFramers(FramersRequest framersRequest);
}
