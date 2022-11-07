package com.michaels.designhub.service;

import com.michaels.designhub.dto.UpdateOfflinePINRequest;
import com.michaels.designhub.dto.UpdateOfflinePINResponse;
import com.michaels.designhub.entity.OfflinePIN;

/**
 * @Author Baojian Hong
 * @Date 2022/9/5 14:12
 * @Version 1.0
 */
public interface OfflinePINService {
    OfflinePIN getPin(String storeId);

    UpdateOfflinePINResponse UpdatePin(UpdateOfflinePINRequest updateOfflinePINRequest);
}
