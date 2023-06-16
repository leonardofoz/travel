package com.afkl.travel.exercise.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {

    private final Counter totalRequestsCounter;
    private final Counter okRequestsCounter;
    private final Counter clientErrorRequestsCounter;
    private final Counter serverErrorRequestsCounter;

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

    public void incrementTotalRequests() {
        totalRequestsCounter.increment();
    }

    public void incrementOkRequests() {
        okRequestsCounter.increment();
    }

    public void incrementClientErrorRequests() {
        clientErrorRequestsCounter.increment();
    }

    public void incrementServerErrorRequests() {
        serverErrorRequestsCounter.increment();
    }
}
