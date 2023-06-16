package com.afkl.travel.exercise.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

/**
 * Service class that provides operations related to metrics.
 *
 * @author leonardofoz
 * @since 0.1.0
 */
@Service
public class MetricsService {

    private final Counter totalRequestsCounter;
    private final Counter okRequestsCounter;
    private final Counter clientErrorRequestsCounter;
    private final Counter serverErrorRequestsCounter;

    /**
     * Constructs a MetricsService with the specified MeterRegistry.
     *
     * @param meterRegistry the MeterRegistry used for registering metrics
     */
    public MetricsService(MeterRegistry meterRegistry) {
        totalRequestsCounter = Counter.builder("api.requests.total")
                .description("Total number of requests processed")
                .register(meterRegistry);

        okRequestsCounter = Counter.builder("api.requests.ok")
                .description("Total number of requests resulted in an OK response")
                .register(meterRegistry);

        clientErrorRequestsCounter = Counter.builder("api.requests.client_error")
                .description("Total number of requests resulted in a 4xx response")
                .register(meterRegistry);

        serverErrorRequestsCounter = Counter.builder("api.requests.server_error")
                .description("Total number of requests resulted in a 5xx response")
                .register(meterRegistry);
    }

    /**
     * Increments the total requests counter.
     */
    public void incrementTotalRequests() {
        totalRequestsCounter.increment();
    }

    /**
     * Increments the OK requests counter.
     */
    public void incrementOkRequests() {
        okRequestsCounter.increment();
    }

    /**
     * Increments the client error requests counter.
     */
    public void incrementClientErrorRequests() {
        clientErrorRequestsCounter.increment();
    }

    /**
     * Increments the server error requests counter.
     */
    public void incrementServerErrorRequests() {
        serverErrorRequestsCounter.increment();
    }
}
