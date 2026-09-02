package com.diet.app.dto;

import org.springframework.http.MediaType;

public class LoadFoodDataSchemaV1 {
    private MediaType dataType;
    private long size;
    private String source;
    private int schemaVersion;

    private String payload;
}
