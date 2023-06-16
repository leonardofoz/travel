package com.afkl.travel.exercise.controller;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Controller class that provides endpoint to retrieve various metrics data.
 *
 * @author leonardofoz
 * @since 0.1.0
 */
@RestController
@RequestMapping("${actuator.metrics.controller.path}")
@AllArgsConstructor
public class MetricsController {

    private final MeterRegistry meterRegistry;

    /**
     * Retrieves the metrics data including total requests, response counts, and response times.
     *
     * @return A map containing the metrics data.
     */
    @GetMapping
    public Map<String, Object> getMetrics() {
        Map<String, Object> metricsData = new HashMap<>();

        metricsData.put("Total number of requests processed: ", getTotalRequests());
        metricsData.put("Total number of requests resulted in an OK response: ", getOkRequests());
        metricsData.put("Total number of requests resulted in a 4xx response: ", getClientErrorRequests());
        metricsData.put("Total number of requests resulted in a 5xx response: ", getServerErrorRequests());
        metricsData.put("averageResponseTime", getAverageResponseTime());
        metricsData.put("Max response time of all requests: ", getMaxResponseTime());

        return metricsData;
    }

    private double getTotalRequests() {
        return meterRegistry.find("api.requests.total").counter().count();
    }

    private double getOkRequests() {
        return meterRegistry.find("api.requests.ok").counter().count();
    }

    private double getClientErrorRequests() {
        return meterRegistry.find("api.requests.client_error").counter().count();
    }

    private double getServerErrorRequests() {
        return meterRegistry.find("api.requests.server_error").counter().count();
    }

    private double getAverageResponseTime() {
        Timer timer = meterRegistry.find("http.server.requests").timer();
        if (timer != null) {
            return timer.mean(TimeUnit.MILLISECONDS);
        }
        return 0.0;
    }

    private double getMaxResponseTime() {
        Timer timer = meterRegistry.find("http.server.requests").timer();
        if (timer != null) {
            return timer.max(TimeUnit.MILLISECONDS);
        }
        return 0.0;
    }
}
