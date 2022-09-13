package com.michaels.designhub.colorpickerapi.common.intercepter;

import com.michaels.designhub.colorpickerapi.common.exception.ForbiddenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: TokenInterceptor
 * @Author: Qiang Hu
 * @Date: 9/5/2022 10:05 AM
 */
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authorization = request.getHeader("Authorization");
        if(!StringUtils.hasLength(authorization)){
            throw new ForbiddenException("Authorization is null");
        }else {
            // check token;
        }
        return true;
    }
}
