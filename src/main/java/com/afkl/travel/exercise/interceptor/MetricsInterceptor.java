package com.afkl.travel.exercise.interceptor;

import com.afkl.travel.exercise.service.MetricsService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interceptor for collecting metrics related to HTTP requests.
 *
 * @author leonardofoz
 * @since 0.1.0
 */
public class MetricsInterceptor implements HandlerInterceptor {

    private final MetricsService metricsService;

    /**
     * Constructs a MetricsInterceptor with the provided MetricsService.
     * @param metricsService the MetricsService used for collecting metrics
     */
    public MetricsInterceptor(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    /**
     * Pre-handle method that gets called before the request is processed.
     * Increments the total number of requests metric in the MetricsService.
     * @param request the HttpServletRequest object representing the incoming request
     * @param response the HttpServletResponse object representing the outgoing response
     * @param handler the handler object for the request
     * @return true to proceed with the request handling, false otherwise
     * @throws Exception if an error occurs during pre-handle processing
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        metricsService.incrementTotalRequests();
        return true;
    }

    /**
     * Post-handle method that gets called after the request is processed.
     * Increments the appropriate metric based on the response status code in the MetricsService.
     * @param request the HttpServletRequest object representing the incoming request
     * @param response the HttpServletResponse object representing the outgoing response
     * @param handler the handler object for the request
     * @param modelAndView the ModelAndView object for the request
     * @throws Exception if an error occurs during post-handle processing
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
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