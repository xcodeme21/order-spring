package com.example.advice;

import java.util.UUID;

public class ResponseHelper {

    private static final String APP_NAME = "Order Spring";
    private static final String BUILD = "1";
    private static final String VERSION = "1.0.1.3";

    public static <T> WebResponse<T> ok(T data, String message) {
        return WebResponse.<T>builder()
                .app_name(APP_NAME)
                .build(BUILD)
                .version(VERSION)
                .id(UUID.randomUUID().toString())
                .message(message)
                .data(data)
                .build();
    }

    public static <T> WebResponse<T> error(String message) {
        return WebResponse.<T>builder()
                .app_name(APP_NAME)
                .build(BUILD)
                .version(VERSION)
                .id(UUID.randomUUID().toString())
                .message(message)
                .data(null)
                .build();
    }
}
