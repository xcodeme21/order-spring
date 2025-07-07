package com.example.advice;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ResponseHelper {

    private final AppProperties props;

    public ResponseHelper(AppProperties props) {
        this.props = props;
    }

    public <T> WebResponse<T> ok(T data, String message) {
        return WebResponse.<T>builder()
                .app_name(props.getAppName())
                .build(props.getBuild())
                .version(props.getVersion())
                .id(UUID.randomUUID().toString())
                .message(message)
                .data(data)
                .build();
    }

    public <T> WebResponse<T> error(String message) {
        return WebResponse.<T>builder()
                .app_name(props.getAppName())
                .build(props.getBuild())
                .version(props.getVersion())
                .id(UUID.randomUUID().toString())
                .message(message)
                .data(null)
                .build();
    }
}
