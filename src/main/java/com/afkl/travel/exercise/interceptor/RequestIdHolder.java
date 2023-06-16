package com.afkl.travel.exercise.interceptor;

/**
 * Utility class that holds the request ID for the current thread.
 */
public class RequestIdHolder {

    private static final ThreadLocal<String> requestIdHolder = new ThreadLocal<>();

    public static String getRequestId() {
        return requestIdHolder.get();
    }

    public static void setRequestId(String requestId) {
        requestIdHolder.set(requestId);
    }

    public static void clearRequestId() {
        requestIdHolder.remove();
    }
}

