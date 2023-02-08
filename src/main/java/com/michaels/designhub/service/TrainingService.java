package com.michaels.designhub.service;

import com.alibaba.fastjson.JSONObject;


public interface TrainingService {

    /**
     * save Training Logging
     * POST- /v1/utils/training
     *
     * @param trainingObject
     * @return id
     */
    String saveTrainingLog(JSONObject trainingObject);

    String updateTrainingLog(JSONObject trainingPutObject);
}
