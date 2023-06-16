package com.afkl.travel.exercise.config;

import com.afkl.travel.exercise.interceptor.RequestTrackingInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class that registers the RequestTrackingInterceptor
 * to be applied to incoming requests.
 *
 * @author leonardofoz
 * @since 0.1.0
 */
@Configuration
@AllArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private RequestTrackingInterceptor requestTrackingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestTrackingInterceptor);
    }
}
