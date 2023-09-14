package com.michaels.designhub.service.impl;

import com.michaels.designhub.dto.UpdateOfflinePINRequest;
import com.michaels.designhub.dto.UpdateOfflinePINResponse;
import com.michaels.designhub.entity.OfflinePIN;
import com.michaels.designhub.repository.OfflinePINRepository;
import com.michaels.designhub.service.OfflinePINService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author Baojian Hong
 * @Date 2022/9/5 14:13
 * @Version 1.0
 */
@Service
@Slf4j
public class OfflinePINServiceImpl implements OfflinePINService {

    @Autowired
    private OfflinePINRepository offlinePINRepository;

    @Override
    public OfflinePIN getPin(String storeId) {
        log.debug("getPin - getPin params:{}.",storeId);
        List<OfflinePIN> list = offlinePINRepository.selectByStoreId(storeId);
        return list.get(0);
    }

    @Override
    public UpdateOfflinePINResponse UpdatePin(UpdateOfflinePINRequest updateOfflinePINRequest) {
        log.debug("UpdatePin - Update Pin params:{}.",updateOfflinePINRequest);
        UpdateOfflinePINResponse pinResponse = new UpdateOfflinePINResponse();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Integer count  = offlinePINRepository.upPin(updateOfflinePINRequest.getStore_id(),updateOfflinePINRequest.getHashed_pin(),updateOfflinePINRequest.getEncrypted_pin(),updateOfflinePINRequest.getExpiry_date(),simpleDateFormat.format(new Date()));
        if (count == 1){
            pinResponse.setStatus_code(1);
            pinResponse.setStatus_message("Success");
        }else{
            log.debug("UpdatePin - Update Pin Failure.");
            pinResponse.setStatus_code(0);
            pinResponse.setStatus_message("Failure");
        }
        return pinResponse;
    }
}
