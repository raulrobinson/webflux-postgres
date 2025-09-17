package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "openapi.service")
public record OpenApiProps(
        String title,
        String version,
        String url,
        String description,
        ContactProps contact,
        String host
) {
    public record ContactProps(String email, String name) {}
}
