package com.diet.app.service;

import com.diet.app.dto.LoadFoodDataSchema;
import com.diet.app.enums.Format;
import com.diet.app.model.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PayloadResolver {

    private final Map<Format, PayloadDecoder> payloadDecoderMap;

    public PayloadResolver(Map<Format, PayloadDecoder> payloadDecoderMap) {
        this.payloadDecoderMap = payloadDecoderMap;
    }

    public Payload resolve(LoadFoodDataSchema loadFoodDataSchema){

    }
}
