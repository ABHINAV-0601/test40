package com.michaels.designhub.controller;

import com.alibaba.fastjson.JSONObject;
import com.michaels.designhub.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/v1")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    /** If a user selects training mode in UI,
     *  if checkbox is enabled,
     *      request     : firstname, lastname, role and store id.
     *      response    : id
     *  else If checkbox is disabled,
     *      request     : id
     *      response    : success/failure
     */

    @ResponseBody
    @PostMapping("/utils/training")
    public Object trainingPOST(@RequestBody JSONObject trainingObject) {
        return trainingService.saveTrainingLog(trainingObject);
    }

    @PutMapping("/utils/training")
    public void trainingPUT(@RequestBody JSONObject trainingPutObject) {
        trainingService.updateTrainingLog(trainingPutObject);
    }
}
