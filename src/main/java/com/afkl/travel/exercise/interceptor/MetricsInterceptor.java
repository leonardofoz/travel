package com.afkl.travel.exercise.interceptor;

import com.afkl.travel.exercise.service.MetricsService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MetricsInterceptor implements HandlerInterceptor {

    private final MetricsService metricsService;

    public MetricsInterceptor(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Increment total requests counter
        metricsService.incrementTotalRequests();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Check response status and increment corresponding counters
        int statusCode = response.getStatus();
        if (statusCode >= 200 && statusCode < 300) {
            metricsService.incrementOkRequests();
        } else if (statusCode >= 400 && statusCode < 500) {
            metricsService.incrementClientErrorRequests();
        } else if (statusCode >= 500) {
            metricsService.incrementServerErrorRequests();
        }
    }
}