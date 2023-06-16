package com.afkl.travel.exercise.config;

import com.afkl.travel.exercise.interceptor.MetricsInterceptor;
import com.afkl.travel.exercise.service.MetricsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class MetricsConfig implements WebMvcConfigurer {

    private final MetricsService metricsService;

    @Bean
    public MetricsInterceptor metricsInterceptor() {
        return new MetricsInterceptor(metricsService);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(metricsInterceptor());
    }
}
