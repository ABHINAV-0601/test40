package com.michaels.designhub.colorpickerapi.common.config;

import com.michaels.designhub.colorpickerapi.common.intercepter.TokenInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @author Qiang Hu
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Value("${design.hub.token.excludePathPatterns}")
    private String  excludePathPatterns;

    /**
     * Interceptors
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // ignore List
        List<String> ignoreList =Arrays.asList(excludePathPatterns.split(","));
        // Interceptor path
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**").excludePathPatterns(ignoreList);
    }
    /**
     * Cors Registry
     *
     * @param registry
     */
   @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS", "HEAD")
                .maxAge(3600 * 24);
    }

    /**
     *  Exchange MappingJackson2HttpMessageConverter
     * Allow interfaces that return String to return wrapper results normally
     *
     * @param converters initially an empty list of converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (int i = 0; i < converters.size(); i++) {
            if (converters.get(i) instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = (MappingJackson2HttpMessageConverter) converters.get(i);
                converters.set(i, converters.get(0));
                converters.set(0, mappingJackson2HttpMessageConverter);
                break;
            }
        }
    }
}