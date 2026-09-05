package com.diet.app.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;


@Getter
@Setter
public class LoadFoodDataSchema {
    private MediaType dataType;
    private long size;
    private String source;
    private int schemaVersion;
    private String payload;
}
