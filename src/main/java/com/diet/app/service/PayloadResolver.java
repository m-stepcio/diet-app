package com.diet.app.service;

import com.diet.app.dto.LoadFoodDataSchema;
import com.diet.app.enums.Format;
import com.diet.app.model.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;

import static java.util.Objects.isNull;

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

    public Payload resolve(LoadFoodDataSchema loadFoodDataSchema) throws Exception{
        PayloadDecoder decoder = null;

        for(Map.Entry<Format, PayloadDecoder> decoderEntry : payloadDecoderMap.entrySet()){
            if(decoderEntry.getKey().isMediaTypeSupported(loadFoodDataSchema.getDataType())){
                decoder = decoderEntry.getValue();
            }
        }

        if(isNull(decoder)){
            throw new IllegalArgumentException("No decoder found fr media type " + loadFoodDataSchema.getDataType());
        }
        return decoder.decode(loadFoodDataSchema.getPayload());
    }
}
