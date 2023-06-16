package com.afkl.travel.exercise.config;

import com.afkl.travel.exercise.interceptor.MetricsInterceptor;
import com.afkl.travel.exercise.service.MetricsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class that sets up metrics-related configurations for the application.
 *
 * @author leonardofoz
 * @since 0.1.0
 */
@Configuration
@AllArgsConstructor
public class MetricsConfig implements WebMvcConfigurer {

    private final MetricsService metricsService;

    /**
     * Creates and configures the MetricsInterceptor bean.
     *
     * @return The MetricsInterceptor bean.
     */
    @Bean
    public MetricsInterceptor metricsInterceptor() {
        return new MetricsInterceptor(metricsService);
    }

    /**
     * Adds the MetricsInterceptor to the InterceptorRegistry.
     *
     * @param registry The InterceptorRegistry to which the MetricsInterceptor is added.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(metricsInterceptor());
    }
}
