package com.example.advice;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AppProperties {

    @Value("${app.name}")
    private String appName;

    @Value("${app.build}")
    private String build;

    @Value("${app.version}")
    private String version;
}
