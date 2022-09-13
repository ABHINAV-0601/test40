package com.michaels.designhub.colorpickerapi.common.service.impl;

import com.michaels.designhub.colorpickerapi.common.dto.Result;
import com.michaels.designhub.colorpickerapi.common.service.ResponseBodyAdvice;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * wrap return results
 * @author Qiang Hu
 */
@RestControllerAdvice(basePackages = "com.michaels.design.hub")
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // If you do not need to wrap, you can add some validation, such as the annotation of tag exclusion
        return true;
    }
  

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // If the body is already wrapped, it is not wrapped
        if (body instanceof Result) {
            return body;
        }
        return Result.success(body);
    }
}