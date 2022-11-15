package com.michaels.designhub.controller;

import com.michaels.designhub.common.config.SwaggerProperties;
import com.michaels.designhub.common.dto.Result;
import com.michaels.designhub.common.utils.ServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Thomas Tang
 */
@Slf4j
@RestController
public class HealthController {

    @Autowired
    private SwaggerProperties swaggerProperties;

    @GetMapping(value = "/v1/colors/ok", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result<Object> ok() {
        return Result.success(ServerUtil.getServerInfo());
    }

    @GetMapping(value = "/v1/colors/version", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result<Object> version() {
        return Result.success(swaggerProperties);
    }
}
