package com.afkl.travel.exercise.interceptor;

/**
 * Utility class that holds the request ID for the current thread.
 *
 * @author leonardofoz
 * @since 0.1.0
 */
public class RequestIdHolder {

    private static final ThreadLocal<String> requestIdHolder = new ThreadLocal<>();

    /**
    * Retrieves the request ID for the current thread.
    *  @return the request ID
    */
    public static String getRequestId() {
        return requestIdHolder.get();
    }

    /**
    * Sets the request ID for the current thread.
    * @param requestId the request ID to be set
    */
    public static void setRequestId(String requestId) {
        requestIdHolder.set(requestId);
    }

    /**
    * Clears the request ID for the current thread.
    */
    public static void clearRequestId() {
        requestIdHolder.remove();
    }
}

