package com.diet.app.dto;


import org.springframework.http.MediaType;

public record SchemaMetadata(
        MediaType dataTypem,
        long size,
        String source,
        int schemaVersion
) {
}
