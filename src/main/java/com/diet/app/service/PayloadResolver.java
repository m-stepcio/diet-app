package com.diet.app.service;

import com.diet.app.dto.LoadFoodDataSchema;
import com.diet.app.enums.Format;
import com.diet.app.model.Payload;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PayloadResolver {

    private final Map<Format, PayloadDecoder> payloadDecoderMap;

    public PayloadResolver() {
        this.payloadDecoderMap = Map.ofEntries(
                Map.entry(Format.CSV, new CsvDecoder()),
                Map.entry(Format.XML, new XmlDecoder()),
                Map.entry(Format.JSON, new JsonDecoder())
        );
    }

    public Payload resolve(LoadFoodDataSchema loadFoodDataSchema){
        return null;
    }
}
