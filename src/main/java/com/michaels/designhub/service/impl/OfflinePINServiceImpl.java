package com.michaels.designhub.service.impl;

import com.michaels.designhub.entity.OfflinePIN;
import com.michaels.designhub.repository.OfflinePINRepository;
import com.michaels.designhub.service.OfflinePINService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Baojian Hong
 * @Date 2022/9/5 14:13
 * @Version 1.0
 */
@Service
public class OfflinePINServiceImpl implements OfflinePINService {

    @Autowired
    private OfflinePINRepository offlinePINRepository;

    @Override
    public OfflinePIN getPin(String storeId) {
        List<OfflinePIN> list = offlinePINRepository.selectByStoreId(storeId);
        return list.get(0);
    }
}
