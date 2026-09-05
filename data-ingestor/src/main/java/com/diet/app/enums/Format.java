package com.diet.app.enums;



import org.springframework.http.MediaType;

import java.util.List;

public enum Format {
    CSV(List.of(MediaType.valueOf("text/csv"))),
    JSON(List.of(MediaType.APPLICATION_JSON)),
    XML(List.of(MediaType.TEXT_XML, MediaType.APPLICATION_XML));

    Format(List<MediaType> supportedMediaType) {
        this.supportedMediaType = supportedMediaType;
    }

    private final List<MediaType> supportedMediaType;

    public boolean isMediaTypeSupported(String mediaType){
        return isMediaTypeSupported(MediaType.valueOf(mediaType));
    }

    public boolean isMediaTypeSupported(MediaType mediaType){
        return supportedMediaType.contains(mediaType);
    }
}
