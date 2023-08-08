package com.michaels.designhub.service.impl;

import com.michaels.designhub.entity.Framer;
import com.michaels.designhub.repository.FramersRepository;
import com.michaels.designhub.request.FramersRequest;
import com.michaels.designhub.service.FramersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Baojian Hong
 * @Date 2022/9/13 10:11
 * @Version 1.0
 */
@Service
@Slf4j
public class FramersServiceImpl implements FramersService {


    @Autowired
    private FramersRepository framersRepository;

    @Override
    public Object getFramersByUsername(String username) {
        //"Fetch Framer details for username -" ++ vars.username
        log.debug("getFramersByUsername - Fetch  Framer details for username - {}. " , username);
        Framer framer = framersRepository.getFramersByUsername(username);
        if (framer == null) {
            log.debug("postFramers - No Framer Data Found.");
            Map<String, String> map = new HashMap<>();
            map.put("Message", "No Framer Data Found");
            return map;
        } else {
            return framer;
        }

    }

    @Override
    public Object postFramers(FramersRequest framersRequest) {
        log.debug("postFramers - Fetch post Framers params - {}." , framersRequest);
        int i = framersRepository.updateFramers(framersRequest.getUpdated_at(), framersRequest.getUsername());
        if (i == 0) {
            // add
            i = framersRepository.addFramers(framersRequest.getFirstname(), framersRequest.getLastname(), framersRequest.getUpdated_at(), framersRequest.getUsername());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("status_code", i);
        if (i == 0) {
            log.debug("postFramers - post Framers Failure.");
            map.put("status_message", "Failure");
        }else{
            map.put("status_message", "Success");
        }
        return map;
    }
}
