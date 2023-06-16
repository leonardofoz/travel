package com.afkl.travel.exercise.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Interceptor that generates a unique request ID for each incoming request.
 */
@Component
public class RequestTrackingInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(RequestTrackingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestId = UUID.randomUUID().toString();
        RequestIdHolder.setRequestId(requestId);
        logger.info("[{}] [PreHandle] [Request: {} {}]", requestId, request.getMethod(), request.getRequestURI());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestId = RequestIdHolder.getRequestId();
        logger.info("[{}] [AfterCompletion] [Response: {}]", requestId, response.getStatus());
        RequestIdHolder.clearRequestId();
    }
}

