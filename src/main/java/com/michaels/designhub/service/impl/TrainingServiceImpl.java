package com.michaels.designhub.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.michaels.designhub.entity.TrainingLog;
import com.michaels.designhub.repository.TrainingLogRepository;
import com.michaels.designhub.service.TrainingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Slf4j
@Service
public class TrainingServiceImpl implements TrainingService {

    private static final String NULL = "null";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String ROLE = "role";
    public static final String STORE_ID = "store_id";
    public static final String ID = "id";

    @Autowired
    private TrainingLogRepository trainingLogRepository;

    /* Method to save data to training_log table in database
     * Gets, firstname, lastname, role, storeId
     * Returns, id
     * */

    @Transactional
    @Override
    public String saveTrainingLog(JSONObject trainingObject) {
        TrainingLog trainingLog = new TrainingLog();
        trainingLog.setFirstName(trainingObject.getString(FIRSTNAME));
        trainingLog.setLastName(trainingObject.getString(LASTNAME));
        trainingLog.setRole(trainingObject.getString(ROLE));
        trainingLog.setStoreId(trainingObject.getString(STORE_ID));
        trainingLog.setCreatedAt(LocalDateTime.now());
        trainingLogRepository.save(trainingLog);
        return trainingLog.getId().toString();
    }

    /*  Method to release lock from database
     *  gets, id
     *  returns, success/failure
     * */

    @Transactional
    public String updateTrainingLog(JSONObject trainingObject){
        if (trainingObject.containsKey(ID)) {
            trainingLogRepository.updateExitedAtBy(LocalDateTime.now(), Integer.parseInt(trainingObject.getString("id")));
            return "true";
        }
        return "false";
    }

}