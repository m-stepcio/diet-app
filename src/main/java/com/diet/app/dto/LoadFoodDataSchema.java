package com.diet.app.dto;

import lombok.Getter;
import org.springframework.http.MediaType;

@Getter
public class LoadFoodDataSchema {
    private MediaType dataType;
    private long size;
    private String source;
    private int schemaVersion;
    private String payload;

    public SchemaMetadata getMetadata(){
        return new SchemaMetadata(dataType, size, source, schemaVersion);
    }
}
